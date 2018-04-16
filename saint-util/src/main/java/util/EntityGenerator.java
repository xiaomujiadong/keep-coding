package util;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.filechooser.FileSystemView;

public class EntityGenerator {
    private static final String USER = "root";
    private static final String PASS = "123456";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    // 数据库连接,不使用密码
    private static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=" + USER
            + "&useUnicode=true&characterEncoding=UTF-8";
    // 数据库连接,使用密码
//    private static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=" + USER+"&password="+PASS
//            + "&useUnicode=true&characterEncoding=UTF-8";
    private static final String SAVE_CATALOG = "E:\\test";
    private static final String SAVE_PACKAGE = "com.saint.entity";

    private static Connection getConnection = null;

    public EntityGenerator() {
        super();
        getConnection = getConnections();

        DatabaseMetaData dbmd;
        try {
            dbmd = getConnection.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[] {"TABLE"});
            // 迭代所有的表
            while (resultSet.next()) {
                // 表名
                String tableName = resultSet.getString("TABLE_NAME");
                // 字段信息集合
                ResultSet colrs = dbmd.getColumns(null, "%", tableName, "%");

                // 创建类文件
                if (getSavePath() == null)
                    break;
                String javaFileName = getClassNameWithTableName(tableName);
                String classFilePath = getSavePath() + initialCap(javaFileName) + ".java";
                // 文件输出流
                FileWriter fw = new FileWriter(classFilePath);
                PrintWriter pw = new PrintWriter(fw);
                // 先写package import文本
                StringBuffer fileBuffer = new StringBuffer();
                if (SAVE_PACKAGE != null && !SAVE_PACKAGE.equals(""))
                    fileBuffer.append("package " + SAVE_PACKAGE + ";\r\n");

                // 类实体文本
                StringBuffer contentBuffer = new StringBuffer();
                // 类名称
                contentBuffer.append("\r\n\r\npublic class " + javaFileName + "{\r\n");
                // 属性文本
                StringBuffer attrBuffer = new StringBuffer();
                // 方法文本
                StringBuffer methodBuffer = new StringBuffer("\r\n");

                // 写入属性 setter和getter方法
                while (colrs.next()) {
                    addField(attrBuffer, colrs);
                    if (sqlType2JavaType(colrs.getString("TYPE_NAME")).equals("Date")) {
                        fileBuffer.append("import java.util.Date;\r\n");
                    }
                    addMethod(methodBuffer, colrs);
                }

                contentBuffer.append(attrBuffer);
                contentBuffer.append(methodBuffer);
                contentBuffer.append("}\r\n");

                fileBuffer.append(contentBuffer);

                pw.println(fileBuffer.toString());

                pw.flush();
                pw.close();

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 建立数据库连接
     *
     * @return
     */
    public static Connection getConnections() {
        try {
            // Properties props =new Properties();
            // props.put("remarksReporting","true");
            Class.forName(DRIVER);
            getConnection = DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getConnection;
    }


    public static String getSavePath() {
        String path = null;
        if (SAVE_CATALOG == null || SAVE_CATALOG.equals("")) {
            // 获取当前用户桌面路径
            FileSystemView fsv = FileSystemView.getFileSystemView();
            path = fsv.getHomeDirectory().toString();

        } else {
            path = SAVE_CATALOG;
        }

        if (!SAVE_PACKAGE.equals(""))
            path = path + "/" + SAVE_PACKAGE.replace(".", "/") + "/";
        System.out.println(path);

        // 创建目录
        File dir = new File(path);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("创建目录成功！");
            } else {
                System.out.println("创建目录失败！");
                return null;
            }
        }

        return path;

    }

    /**
     * 将数据表名称转化成类的名称 （下划线后一字符小写转大写，去掉下划线）
     *
     * @param tableName
     * @return
     */
    private static String getClassNameWithTableName(String tableName) {
        if (tableName == null)
            return tableName;
        if (tableName.contains("_")) {
            char[] ch = tableName.toCharArray();
            for (int index = 0; index < ch.length; index++) {
                if (ch[index] == '_' && index > 0 && index < ch.length - 1) {
                    if (ch[index + 1] >= 'a' && ch[index + 1] <= 'z') {
                        ch[index + 1] = (char) (ch[index + 1] - 32);
                    }
                }
            }
            ch[0] = (char) (ch[0] - 32);
            return new String(ch).replace("_", "");
        }
        return tableName;
    }

    /**
     * 将数据表字段转化成类的属性名称 （下划线后一字符小写转大写，去掉下划线）
     *
     * @param columnName
     * @return
     */
    private static String columnName2AttrName(String columnName) {
        if (columnName == null)
            return columnName;
        if (columnName.contains("_")) {
            char[] ch = columnName.toCharArray();
            for (int index = 0; index < ch.length; index++) {
                if (ch[index] == '_' && index > 0 && index < ch.length - 1) {
                    if (ch[index + 1] >= 'a' && ch[index + 1] <= 'z') {
                        ch[index + 1] = (char) (ch[index + 1] - 32);
                    }
                }
            }

            return new String(ch).replace("_", "");
        }
        return columnName;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new EntityGenerator();
    }

    /**
     * 生成成员属性
     *
     * @param contentBuffer
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private StringBuffer addField(StringBuffer contentBuffer, ResultSet resultSet) throws SQLException {
        if (contentBuffer == null || resultSet == null)
            return contentBuffer;
        // 先写注释
        contentBuffer.append("\r\n");
        contentBuffer.append("\t/**\r\n");
        contentBuffer.append("\t *\t" + resultSet.getString("REMARKS") + "\r\n");
        contentBuffer.append("\t */\r\n");

        // 写属性
        contentBuffer.append("\tprivate " + sqlType2JavaType(resultSet.getString("TYPE_NAME")) + " "
                + columnName2AttrName(resultSet.getString("COLUMN_NAME")) + ";\r\n");

        return contentBuffer;
    }

    /**
     * 生成属性setter getter
     *
     * @param contentBuffer
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private StringBuffer addMethod(StringBuffer contentBuffer, ResultSet resultSet) throws SQLException {
        if (contentBuffer == null || resultSet == null)
            return contentBuffer;
        // 属性名
        String attrName = columnName2AttrName(resultSet.getString("COLUMN_NAME"));
        // 属性名首字母大写
        String capMethodName = initialCap(attrName);
        // 属性类型
        String attrType = sqlType2JavaType(resultSet.getString("TYPE_NAME"));

        contentBuffer.append("\tpublic void set" + capMethodName + "(" + attrType + " " + attrName + "){\r\n");
        contentBuffer.append("\t\tthis." + attrName + "=" + attrName + ";\r\n");
        contentBuffer.append("\t}\r\n\r\n");
        contentBuffer.append("\tpublic " + attrType + " get" + capMethodName + "(){\r\n");
        contentBuffer.append("\t\treturn " + attrName + ";\r\n");
        contentBuffer.append("\t}\r\n\r\n");
        return contentBuffer;
    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private String initialCap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "int";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        } else if (sqlType.equalsIgnoreCase("timestamp")) {
            return "Timestamp";
        }

        return null;
    }


}
