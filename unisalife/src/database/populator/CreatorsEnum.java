/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.populator;

/**
 * This enumeration works as a map between the objects that need to be managed
 * (load/save) by the internal database and the factory method to create them.
 * 
 * @author cmarino
 */


public enum CreatorsEnum {
    item("item"){
        @Override
         public SaveableCreator getFactory(){
            return new ItemFactory();
        }
    },professor("professor"){
        @Override
        public SaveableCreator getFactory(){
            return new ProfessorFactory();
        }
    }, subject("subject"){
        @Override
        public SaveableCreator getFactory(){
            return new SubjectFactory();
        }
    }, quest("quest"){
        @Override
        public SaveableCreator getFactory(){
            return new QuestFactory();
        }
    };
    
    private final String type;
    
    CreatorsEnum( String type ){
        this.type = type;
    }
    
    public abstract SaveableCreator getFactory();
    
    public String getType(){
        return this.type;
    }
    
    
    
}
