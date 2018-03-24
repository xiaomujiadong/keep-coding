package com.saint.demo.design.model.command;

public class RemoteControl {

   private Command[] onCommand;
   private Command[] offCommand;

   public RemoteControl(){
       onCommand = new Command[7];
       offCommand = new Command[7];

       NoCommand noCommand = new NoCommand();
       for(int i=0; i<7; i++){
           onCommand[i] = noCommand;
           offCommand[i] = noCommand;
       }
   }

   public void setCommand(int slot, Command onCommand, Command offCommand){
       this.onCommand[slot] = onCommand;
       this.offCommand[slot] = offCommand;
   }

   public void onButtonWasPushed(int slot){
       this.onCommand[slot].execute();
   }

    public void offButtonWasPushed(int slot){
        this.offCommand[slot].execute();
    }
}
