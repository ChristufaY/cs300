//////////////// FILE HEADER (INCLUDE IN EVERY FILE) ///////////////
///////////
//
// Title: P10 Battle System
// Course: CS 300 Fall 2020
//
// Author: Christopher Yang
// Email: cyang397@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP ///////////////
///////////
// Persons: Father
// Online Sources: None
//
////////////////////////////////////////////////////////////////////
///////////
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/** 
 * Main driver class for P10. Reads in character data from a file and then runs and processes the battle.
 * @author Michelle
 *
 */
public class BattleSystem {

  private static MoveQueue currentOrder;
  private static ArrayList<PartyCharacter> party;
  private static ArrayList<EnemyCharacter> enemies;
  private static final String [] COMMANDS = {"ATTACK","CASTSPELL","WAIT","RUN"};
  private static final String [] SPELLS = {"STARSTORM", "HASTE", "SLOW"};
  private static final Scanner READIN = new Scanner(System.in);
  private static Status battleState = Status.CONTINUE;
  
  //Set of enums to limit the different types of statuses.
  //If you're curious about them read this reference: https://www.geeksforgeeks.org/enum-in-java/
  private enum Status
  { WIN, LOSE, FLEE, CONTINUE; }
  
  //Initalizes the battle and reads in and parses character information.
  private static void setUp()
  {
    currentOrder = new MoveQueue();
    party = new ArrayList<PartyCharacter>();
    enemies = new ArrayList<EnemyCharacter>();
    
    //load data from txt file
    List<String> fileLines;

    try
    {
      fileLines = Files.readAllLines(Paths.get("/Users/christopheryang/Desktop/"
          + "Freshman Year/Computer Science 300/Battle System/src/characters.txt"));
    }
    catch(IOException e)
    {
      System.out.println("There was an issue loading the character file.");
      return;
    }
    
    String [] characterInfo;
    for (int i = 0; i < fileLines.size(); i++) {
      //parse info
      characterInfo = fileLines.get(i).split(" ");
      String name = characterInfo[0];
      int healthPoints = Integer.parseInt(characterInfo[1]);
      int attack = Integer.parseInt(characterInfo[2]);
      int defense = Integer.parseInt(characterInfo[3]);
      int magic = Integer.parseInt(characterInfo[4]);
      int speed = Integer.parseInt(characterInfo[5]);
      int[] stats = {healthPoints, attack, defense, magic, speed};
      
      if(characterInfo.length == 7) //is an enemy
      {
        String temperament = characterInfo[6].toUpperCase();
        EnemyCharacter enemy = new EnemyCharacter(name, stats, temperament);
        enemies.add(enemy);
      }
      else //it's a controllable character 
      {
        PartyCharacter member = new PartyCharacter(name,stats);
        party.add(member);
      }
    }
    populateCurrentOrder();
  }
  
//add all existing characters to move order
  private static void populateCurrentOrder()
  {
    for(EnemyCharacter e: enemies)
      currentOrder.enqueue(e);
    
    for(PartyCharacter p: party)
      currentOrder.enqueue(p);
  }
  
  //Determines if the battle has ended due to either group being defeated.
  private static void calculateBattleState()
  {
    
    if( enemies.isEmpty() || party.isEmpty())
    {
      if(enemies.isEmpty())
        battleState = Status.WIN;
      else
        battleState = Status.LOSE;       
    }
  }
  
  //Handles physical attacks.
  private static void executeAttack(BattleCharacter attacker, BattleCharacter target)
  {
    target.takeDamage(attacker.getAttack());
    System.out.println(attacker.getName() + " attacks "+ target.getName() +"!");

  }
  
  //Handles magical attack
  private static void executeStarStorm(BattleCharacter attacker, BattleCharacter target)
  {
    target.takeDamage(attacker.getMagic());
    System.out.println(attacker.getName() + " hits "+ target.getName() +" with a flurry of stars!");

  }
  
  //Handles haste spell
  private static void executeHaste(BattleCharacter target)
  {
    target.haste();
    System.out.println(target.getName() + "'s speed increased!");
  }
  
  //Handles slow spell
  private static void executeSlow(BattleCharacter target)
  {
    target.slow();
    
    System.out.println(target.getName() + "'s speed decreased!");
  }
  
  //Handles deciding if able to run from battle 
  private static void executeRun (PartyCharacter runner)
  {
    int fastestEnemySpeed = Collections.max(enemies).getSpeed();
    boolean success = runner.run(fastestEnemySpeed);
    
    if(success)
    {
      battleState = Status.FLEE;
    }
    else
      System.out.println("The party tries to run... but fails!");
  }
  
  //Checks that the command from user is an accepted command.
  private static boolean validateCommand(String command)
  {
    for (String s: COMMANDS)
    {
      if(command.equals(s))
        return true;
    }
    
    return false;
  }
  
  //Checks that the spell from the user is an accepted spell.
  private static boolean validateSpell(String spell)
  {
    for (String s: SPELLS)
    {
      if(spell.equals(s))
        return true;
    }
    
    return false;
  }
  
  //Handles prompting user to pick a target from the correct set of characters
  //and gets the corresponding one
  private static BattleCharacter getPlayerTarget(boolean areEnemies) 
  {
    BattleCharacter target = null;
    if(areEnemies) //list of targets is the enemy characters
    {
      //ask for their choice
      System.out.println("Pick a target, using their ID: ");
      for(EnemyCharacter ec : enemies)
        System.out.print(ec.getName()+"("+ec.getID()+")  ");
      System.out.println();
      
      
      //check choice, prompt again if invalid
      do
      {
        int targetID = READIN.nextInt();
        for(EnemyCharacter ec: enemies)
        {
          if(ec.getID() == targetID)
            target = ec;
        }
        if(target==null)
          System.out.println("Invalid target ID, pick again!");
      }while(target == null);
    }
    else //same code but for the party ArrayList
    {
      System.out.println("Pick a target, using their ID: ");
      for(PartyCharacter pc : party)
        System.out.print(pc.getName()+"("+pc.getID()+")  ");
      System.out.println();
      
      do
      {
        int targetID = READIN.nextInt();
        for(PartyCharacter pc: party)
        {
          if(pc.getID() == targetID)
            target = pc;
        }
        
        if(target==null)
          System.out.println("Invalid target ID, pick again!");
       
      } while(target == null);
      
    }
    READIN.nextLine(); //avoid  invalid command after picking a target
    return target;
  }
  
  //removes dead characters from arraylist and updates instance of a character in the priority queue
  private static void updateChangedCharacter(BattleCharacter updated)
  {
    //get rid of the old
    if(updated instanceof EnemyCharacter)
      enemies.remove(updated);
    else if(updated instanceof PartyCharacter)
      party.remove(updated);
    
    if(updated.isAlive()) //is alive should put in the new
    {
      if(updated instanceof EnemyCharacter)
        enemies.add((EnemyCharacter)updated);
      else if(updated instanceof PartyCharacter)
        party.add((PartyCharacter)updated);
    }
    else
      System.out.println(updated.getName()+" was defeated!");
      
    //update the one in the priority queue
    currentOrder.updateCharacter(updated);
  }
  
  //Handles prompting and what to do with commands inputed by the user 
  private static void handlePartyMove(PartyCharacter toMove)
  {
    System.out.println("What will " + toMove.getName() +" do?");
    System.out.println("ATTACK\tCASTSPELL");
    System.out.println("WAIT\tRUN");
    
    String command = READIN.nextLine().trim().toUpperCase();
    boolean valid = validateCommand(command);
    while(!valid) //if it's not a valid command
    {
      System.out.println("Invalid Command! Please try again.");
      command = READIN.nextLine().trim().toUpperCase();
      valid = validateCommand(command); 
    }
    
    if(command.equals("CASTSPELL"))
    {
      System.out.println("Which spell will they cast?");
      System.out.println("STARSTORM\tHASTE\tSLOW");
      command = READIN.nextLine().trim().toUpperCase();
      valid = validateSpell(command);
      
      while(!valid) //if it's not a valid spell
      {
        System.out.println("Invalid Spell! Please try again.");
        System.out.println(command);
        command = READIN.nextLine().trim().toUpperCase();
        valid = validateSpell(command); 
      }     
    }
    
    BattleCharacter target = null;
    
    //call the correct command
    switch(command)
    {
      case "WAIT":
        System.out.println(toMove.getName() + " is cautiously waiting for their next move.");
        break;
      case "ATTACK":
        target =  getPlayerTarget(true);
        executeAttack(toMove,target);
        break;
      case "STARSTORM":
        target =  getPlayerTarget(true);
        executeStarStorm(toMove, target);
        break;
      case "HASTE":
        target =  getPlayerTarget(false);
        executeHaste(target);
        break;
      case "SLOW":
        target =  getPlayerTarget(true);
        executeSlow(target);
        break;
      case "RUN":
        executeRun(toMove);
        break;
    }
    
    //update the target
    if(target != null)
      updateChangedCharacter(target);
  }
  
  //Gets enemy character to pick a move and target. Then executes the correct command.
  private static void handleEnemyMove(EnemyCharacter toMove)
  {
    String commandChoice = toMove.pickMove(); //"AI" determines what to do
    int targetIndex;
    BattleCharacter target=null;
    
    //execute the corresponding command
    switch(commandChoice)
    {
      case "WAIT":
        System.out.println(toMove.getName() + " is cautiously waiting for its next move.");
        break;
      case "ATTACK":
        targetIndex = toMove.pickTarget(party.size()); //pick target and get it
        target = party.get(targetIndex);
        executeAttack(toMove, target);
        
        break;
      case "STARSTORM":
        targetIndex = toMove.pickTarget(party.size());
        target = party.get(targetIndex);
        executeStarStorm(toMove, target);
        break;
      case "HASTE":
        targetIndex = toMove.pickTarget(enemies.size());
        target = enemies.get(targetIndex);
        System.out.println(toMove.getName() + " casts haste!");
        executeHaste(target);
        break;
      case "SLOW":
        targetIndex = toMove.pickTarget(party.size());
        target = party.get(targetIndex);
        System.out.println(toMove.getName() + " casts slow!");
        executeSlow(target);
        break;
    }
    
    //update
    if(target != null)
      updateChangedCharacter(target);
  }
  
  //Code for one complete turn, where all characters get to move 
  private static void runRound()
  {
    while(!currentOrder.isEmpty() && battleState == Status.CONTINUE) //battle still goes on
    {
      BattleCharacter currChara = currentOrder.dequeue(); //get next to move
      
      if(currChara instanceof EnemyCharacter) //enemy move
      {
        handleEnemyMove((EnemyCharacter)currChara);
      }
      else if(currChara instanceof PartyCharacter) //player move
      {
        handlePartyMove((PartyCharacter) currChara);
      }
      
      // CHECKPOINT: Uncomment the following line.
      // System.out.println(currentOrder);
      calculateBattleState();
    }
  }
  
  //Determines what to do when the battle ends depending on the status
  private static void processBattleEnd()
  {
    switch(battleState)
    {
      case WIN: 
        System.out.println("Congrats! The party lives to see another day!");
        break;
      case LOSE:
        System.out.println("The party fought bravely, but alas they succumbed to their battle injuries...");
        break;
      case FLEE:
        System.out.println("The party successfully flees from the battle!");
        break;
      case CONTINUE:
        break;
        
    }
  }
  
  public static void main(String []args)
  {
    setUp();
    // CHECKPOINT: Uncomment the following line, to help see if enqueue() working.
     System.out.println(currentOrder);
    while(battleState == Status.CONTINUE)
    {
      runRound();
      populateCurrentOrder();
    }
    
      
    
    processBattleEnd();
    
  }
}