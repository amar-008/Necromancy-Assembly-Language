package mars.mips.instructions.customlangs;
import mars.simulator.*;
import mars.mips.hardware.*;
import mars.*;
import mars.util.*;
import mars.mips.instructions.*;

public class NecromancyAssembly extends CustomAssembly{
    @Override
    public String getName(){
        return "Necromancy Assembly";
    }

    @Override
    public String getDescription(){
        return "Dark magic assembly language - Command the undead forces of your CPU";
    }

    @Override
    protected void populate(){
        // ========== BASIC INSTRUCTIONS ==========
        
        // 1. SUMMON - Add (summon power)
        instructionList.add(
                new BasicInstruction("summon $t1,$t2,$t3",
                "Summon: Combine souls from ($t2) and ($t3), manifest in ($t1)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 100001",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int souls1 = RegisterFile.getValue(operands[1]);
                     int souls2 = RegisterFile.getValue(operands[2]);
                     int totalSouls = souls1 + souls2;
                     RegisterFile.updateRegister(operands[0], totalSouls);
                  }
               }));
        
        // 2. DRAIN - Subtract (drain life force)
        instructionList.add(
                new BasicInstruction("drain $t1,$t2,$t3",
                "Drain: Siphon ($t3) life force from ($t2), store essence in ($t1)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 100010",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int victim = RegisterFile.getValue(operands[1]);
                     int drainAmount = RegisterFile.getValue(operands[2]);
                     int remaining = victim - drainAmount;
                     RegisterFile.updateRegister(operands[0], remaining);
                  }
               }));
        
        // 3. MULTIPLY - Multiplication (clone undead)
        instructionList.add(
                new BasicInstruction("clone $t1,$t2,$t3",
                "Clone: Create ($t3) copies of ($t2) undead minions, store in ($t1)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 011000",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int original = RegisterFile.getValue(operands[1]);
                     int copies = RegisterFile.getValue(operands[2]);
                     int army = original * copies;
                     RegisterFile.updateRegister(operands[0], army);
                  }
               }));
        
        // 4. DIVIDE - Division (split soul)
        instructionList.add(
                new BasicInstruction("splitsoul $t1,$t2,$t3",
                "Split Soul: Divide ($t2) soul into ($t3) fragments, store in ($t1)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 011010",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int soul = RegisterFile.getValue(operands[1]);
                     int fragments = RegisterFile.getValue(operands[2]);
                     if (fragments == 0) {
                        throw new ProcessingException(statement, "Cannot split soul into zero fragments");
                     }
                     int fragment = soul / fragments;
                     RegisterFile.updateRegister(operands[0], fragment);
                  }
               }));
        
        // 5. BIND - Bitwise AND (bind spirits)
        instructionList.add(
                new BasicInstruction("bind $t1,$t2,$t3",
                "Bind: Bind spirits from ($t2) with ($t3), store bound entity in ($t1)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 100100",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int spirit1 = RegisterFile.getValue(operands[1]);
                     int spirit2 = RegisterFile.getValue(operands[2]);
                     int bound = spirit1 & spirit2;
                     RegisterFile.updateRegister(operands[0], bound);
                  }
               }));
        
        // 6. MERGE - Bitwise OR (merge souls)
        instructionList.add(
                new BasicInstruction("merge $t1,$t2,$t3",
                "Merge: Merge souls from ($t2) and ($t3), create hybrid in ($t1)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 100101",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int soul1 = RegisterFile.getValue(operands[1]);
                     int soul2 = RegisterFile.getValue(operands[2]);
                     int hybrid = soul1 | soul2;
                     RegisterFile.updateRegister(operands[0], hybrid);
                  }
               }));
        
        // 7. CORRUPT - Bitwise XOR (corrupt essence)
        instructionList.add(
                new BasicInstruction("corrupt $t1,$t2,$t3",
                "Corrupt: Corrupt ($t2) with dark essence from ($t3), store in ($t1)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 100110",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int pure = RegisterFile.getValue(operands[1]);
                     int darkness = RegisterFile.getValue(operands[2]);
                     int corrupted = pure ^ darkness;
                     RegisterFile.updateRegister(operands[0], corrupted);
                  }
               }));
        
        // 8. BANISH - Immediate load (banish to register)
        instructionList.add(
                new BasicInstruction("banish $t1,-666",
                "Banish: Trap immediate value into the void of ($t1)",
                BasicInstructionFormat.I_FORMAT,
                "001000 fffff 00000 ssssssssssssssss",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int value = operands[1] << 16 >> 16; // Sign extend
                     RegisterFile.updateRegister(operands[0], value);
                  }
               }));
        
        // 9. HAUNT - Branch if equal (haunt location)
        instructionList.add(
                new BasicInstruction("haunt $t1,$t2,label",
                "Haunt: If ($t1) souls equal ($t2) souls, haunt the label location",
                BasicInstructionFormat.I_BRANCH_FORMAT,
                "000100 fffff sssss tttttttttttttttt",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     if (RegisterFile.getValue(operands[0]) == RegisterFile.getValue(operands[1]))
                     {
                        Globals.instructionSet.processBranch(operands[2]);
                     }
                  }
               }));
        
        // 10. CURSE - Branch if not equal (curse location)
        instructionList.add(
                new BasicInstruction("curse $t1,$t2,label",
                "Curse: If ($t1) and ($t2) differ, curse jumps to label",
                BasicInstructionFormat.I_BRANCH_FORMAT,
                "000101 fffff sssss tttttttttttttttt",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     if (RegisterFile.getValue(operands[0]) != RegisterFile.getValue(operands[1]))
                     {
                        Globals.instructionSet.processBranch(operands[2]);
                     }
                  }
               }));
        
        // ========== CREATIVE/UNIQUE INSTRUCTIONS ==========
        
        // 11. RESURRECT - Restore register from "death" (zero)
        instructionList.add(
                new BasicInstruction("resurrect $t1,$t2",
                "Resurrect: Bring ($t1) back to life with the soul count from ($t2)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss 00000 fffff 00000 101010",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int newLife = RegisterFile.getValue(operands[1]);
                     RegisterFile.updateRegister(operands[0], Math.abs(newLife));
                  }
               }));
        
        // 12. DECAY - Reduce register value by half (decay over time)
        instructionList.add(
                new BasicInstruction("decay $t1",
                "Decay: The power in ($t1) decays by half as time passes",
                BasicInstructionFormat.R_FORMAT,
                "000000 fffff 00000 00000 00000 101011",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int current = RegisterFile.getValue(operands[0]);
                     RegisterFile.updateRegister(operands[0], current / 2);
                  }
               }));
        
        // 13. POSSESSION - Swap two registers (possess each other)
        instructionList.add(
                new BasicInstruction("possess $t1,$t2",
                "Possession: Spirits in ($t1) and ($t2) possess each other's vessels",
                BasicInstructionFormat.R_FORMAT,
                "000000 fffff sssss 00000 00000 101100",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int temp = RegisterFile.getValue(operands[0]);
                     RegisterFile.updateRegister(operands[0], RegisterFile.getValue(operands[1]));
                     RegisterFile.updateRegister(operands[1], temp);
                  }
               }));
        
        // 14. RITUAL - Sum all temporary registers into target
        instructionList.add(
                new BasicInstruction("ritual $t1",
                "Ritual: Perform dark ritual, gathering all $t register souls into ($t1)",
                BasicInstructionFormat.R_FORMAT,
                "000000 fffff 00000 00000 00000 101101",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int targetReg = operands[0];
                     int totalSouls = 0;
                     // Sum all $t registers (8-15)
                     for (int i = 8; i <= 15; i++){
                        totalSouls += RegisterFile.getValue(i);
                     }
                     RegisterFile.updateRegister(targetReg, totalSouls);
                  }
               }));
        
        // 15. PHYLACTERY - Store register to memory (create phylactery)
        instructionList.add(
                new BasicInstruction("phylactery $t1,100($t2)",
                "Phylactery: Store ($t1) soul in phylactery at memory[($t2) + offset]",
                BasicInstructionFormat.I_FORMAT,
                "101011 sssss fffff tttttttttttttttt",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int address = RegisterFile.getValue(operands[2]) + operands[1];
                     try {
                        Globals.memory.setWord(address, RegisterFile.getValue(operands[0]));
                     } catch (AddressErrorException e) {
                        throw new ProcessingException(statement, e);
                     }
                  }
               }));
        
        // 16. UNEARTH - Load from memory (unearth from grave)
        instructionList.add(
                new BasicInstruction("unearth $t1,100($t2)",
                "Unearth: Dig up soul from grave at memory[($t2) + offset], place in ($t1)",
                BasicInstructionFormat.I_FORMAT,
                "100011 sssss fffff tttttttttttttttt",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int address = RegisterFile.getValue(operands[2]) + operands[1];
                     try {
                        RegisterFile.updateRegister(operands[0], Globals.memory.getWord(address));
                     } catch (AddressErrorException e) {
                        throw new ProcessingException(statement, e);
                     }
                  }
               }));
        
        // 17. VAMPIRE - Steal half from another register and add to self
        instructionList.add(
                new BasicInstruction("vampire $t1,$t2",
                "Vampire: ($t1) drains half the life force from ($t2) and absorbs it",
                BasicInstructionFormat.R_FORMAT,
                "000000 fffff sssss 00000 00000 101110",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int victim = RegisterFile.getValue(operands[1]);
                     int stolen = victim / 2;
                     RegisterFile.updateRegister(operands[1], victim - stolen);
                     int vampireLife = RegisterFile.getValue(operands[0]);
                     RegisterFile.updateRegister(operands[0], vampireLife + stolen);
                  }
               }));
        
        // 18. LICH - Make register immortal (set to max positive value)
        instructionList.add(
                new BasicInstruction("lich $t1",
                "Lich: Transform ($t1) into immortal lich with maximum power",
                BasicInstructionFormat.R_FORMAT,
                "000000 fffff 00000 00000 00000 101111",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     RegisterFile.updateRegister(operands[0], Integer.MAX_VALUE);
                  }
               }));
        
        // 19. NECROMANCY - If register dead (zero), bring back with value from another
        instructionList.add(
                new BasicInstruction("necromancy $t1,$t2",
                "Necromancy: If ($t1) is dead (zero), resurrect with power from ($t2)",
                BasicInstructionFormat.R_FORMAT,
                "000000 fffff sssss 00000 00000 110000",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     if (RegisterFile.getValue(operands[0]) == 0) {
                        RegisterFile.updateRegister(operands[0], RegisterFile.getValue(operands[1]));
                     }
                  }
               }));
        
        // 20. SOULREAD - Print string from memory (read soul inscription)
        instructionList.add(
                new BasicInstruction("soulread label",
                "Soul Read: Read the soul inscription (string) stored at label",
                BasicInstructionFormat.I_BRANCH_FORMAT,
                "110000 00000 00000 ffffffffffffffff",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {        
                     char ch = 0;
                     String label = statement.getOriginalTokenList().get(1).getValue();
                     int byteAddress = Globals.program.getLocalSymbolTable().getAddressLocalOrGlobal(label);

                     try
                        {
                           ch = (char) Globals.memory.getByte(byteAddress);
                           while (ch != 0)
                           {
                              SystemIO.printString(String.valueOf(ch));
                              byteAddress++;
                              ch = (char) Globals.memory.getByte(byteAddress);
                           }
                        } 
                           catch (AddressErrorException e)
                           {
                              throw new ProcessingException(statement, e);
                           }
                  }
               }));
    }
}