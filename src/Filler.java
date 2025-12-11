    package mars.mips.instructions.customlangs;
    import mars.mips.hardware.*;
    import mars.*;
    import mars.util.*;
    import mars.mips.instructions.*;
    import mars.simulator.Exceptions;
    import java.util.Random;

public class Filler extends CustomAssembly{
    @Override
    public String getName(){
        return "Filler Assembly";
    }

    @Override
    public String getDescription(){
        return "The description of filler assembly is um... uh...";
    }

    @Override
    protected void populate(){
        instructionList.add(
                new BasicInstruction("uh $t1,$t2,-100",
            	 "Adds an immediate uhh value to uh a register: set $t1 to ($t2 plus signed 16-bit immediate)",
                BasicInstructionFormat.I_FORMAT,
                "101010 sssss fffff tttttttttttttttt",
                new SimulationCode()
               {
                   public void simulate(ProgramStatement statement) throws ProcessingException
                  {
                     int[] operands = statement.getOperands();
                     int add1 = RegisterFile.getValue(operands[1]);
                     int add2 = operands[2] << 16 >> 16;
                     int sum = add1 + add2;
                     if ((add1 >= 0 && add2 >= 0 && sum < 0)
                        || (add1 < 0 && add2 < 0 && sum >= 0))
                     {
                        throw new ProcessingException(statement,
                            "arithmetic uhhhh overflow",Exceptions.ARITHMETIC_OVERFLOW_EXCEPTION);
                     }
                     RegisterFile.updateRegister(operands[0], sum);
                  }
               }));
        instructionList.add(
            new BasicInstruction("um $t1,$t2,$t3",
                "Addition with umm overflow : set $t1 to ($t2 plus $t3)",
            BasicInstructionFormat.R_FORMAT,
            "000000 sssss ttttt fffff 00000 100000",
            new SimulationCode()
            {
                public void simulate(ProgramStatement statement) throws ProcessingException
                {
                    int[] operands = statement.getOperands();
                    int add1 = RegisterFile.getValue(operands[1]);
                    int add2 = RegisterFile.getValue(operands[2]);
                    int sum = add1 + add2;
                    if ((add1 >= 0 && add2 >= 0 && sum < 0)
                    || (add1 < 0 && add2 < 0 && sum >= 0))
                    {
                    throw new ProcessingException(statement,
                        "arithmetic umm overflow",Exceptions.ARITHMETIC_OVERFLOW_EXCEPTION);
                    }
                    RegisterFile.updateRegister(operands[0], sum);
                }
            }));
        instructionList.add(
            new BasicInstruction("like $t1,$t2,$t3",
                "bro saying like... but it could mean absolutely anything : set $t1 to ($t2 plus random(0-$t3))",
            BasicInstructionFormat.R_FORMAT,
            "000000 sssss ttttt fffff 00000 100000",
            new SimulationCode()
            {
                public void simulate(ProgramStatement statement) throws ProcessingException
                {
                    int[] operands = statement.getOperands();
                    int add1 = RegisterFile.getValue(operands[1]);
                    int add2 = RegisterFile.getValue(operands[2]);
                    if (add2 != 0) {
                        add2 = (int)(Math.random() * add2);  
                    }
                    int sum = add1 + add2;
                    if ((add1 >= 0 && add2 >= 0 && sum < 0)
                    || (add1 < 0 && add2 < 0 && sum >= 0))
                    {
                    throw new ProcessingException(statement,
                        "arithmetic umm overflow",Exceptions.ARITHMETIC_OVERFLOW_EXCEPTION);
                    }
                    RegisterFile.updateRegister(operands[0], sum);
                }
            }));
        instructionList.add(
            new BasicInstruction("uknow $t0",
                "when ur friend asks if u know what they are talking about which most of the time u dont : set 0 to $t0",
                BasicInstructionFormat.I_FORMAT,
                "111111 00000 fffff 0000000000000000",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        RegisterFile.updateRegister(operands[0], 0);
                        SystemIO.printString("no i dont have any idea what ure talking about");
                    }
                }
            )
        );
        instructionList.add(
            new BasicInstruction("bruh $t1,$t2,$t3",
                "subtraction but said \"bruh\": set $t1 to ($t2 minus $t3)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 100010",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        int sub1 = RegisterFile.getValue(operands[1]);
                        int sub2 = RegisterFile.getValue(operands[2]);
                        long result = (long)sub1 - (long)sub2;
                        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                            throw new ProcessingException(statement,
                                "arithmetic bruh overflow", Exceptions.ARITHMETIC_OVERFLOW_EXCEPTION);
                        }
                        RegisterFile.updateRegister(operands[0], (int)result);
                    }
                }
            )
        );

        // nah: bitwise AND
        instructionList.add(
            new BasicInstruction("nah $t1,$t2,$t3",
                "bitwise AND but like \"nah\": set $t1 to ($t2 & $t3)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 100100",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        int v1 = RegisterFile.getValue(operands[1]);
                        int v2 = RegisterFile.getValue(operands[2]);
                        RegisterFile.updateRegister(operands[0], v1 & v2);
                    }
                }
            )
        );

        // orlike: bitwise OR
        instructionList.add(
            new BasicInstruction("orlike $t1,$t2,$t3",
                "bitwise OR but like... \"or like\": set $t1 to ($t2 | $t3)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 100101",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        int v1 = RegisterFile.getValue(operands[1]);
                        int v2 = RegisterFile.getValue(operands[2]);
                        RegisterFile.updateRegister(operands[0], v1 | v2);
                    }
                }
            )
        );

        // yeno: bitwise XOR
        instructionList.add(
            new BasicInstruction("yeno $t1,$t2,$t3",
                "when you say \"ya know\" but you actually mean XOR: set $t1 to ($t2 ^ $t3)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 100110",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        int v1 = RegisterFile.getValue(operands[1]);
                        int v2 = RegisterFile.getValue(operands[2]);
                        RegisterFile.updateRegister(operands[0], v1 ^ v2);
                    }
                }
            )
        );

        // frfr: set-on-less-than (slt)
        instructionList.add(
            new BasicInstruction("frfr $t1,$t2,$t3",
                "for real for real: set $t1 to 1 if $t2 < $t3, else 0",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 101010",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        int v1 = RegisterFile.getValue(operands[1]);
                        int v2 = RegisterFile.getValue(operands[2]);
                        RegisterFile.updateRegister(operands[0], (v1 < v2) ? 1 : 0);
                    }
                }
            )
        );

        // btw: add immediate, but chill about overflow (no exception)
        instructionList.add(
            new BasicInstruction("btw $t1,$t2,-100",
                "just casually adds an immediate: set $t1 to ($t2 plus signed 16-bit immediate), no overflow",
                BasicInstructionFormat.I_FORMAT,
                "001010 sssss fffff tttttttttttttttt",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        int add1 = RegisterFile.getValue(operands[1]);
                        int add2 = operands[2] << 16 >> 16; // sign-extend
                        int sum = add1 + add2;              // overflow ignored
                        RegisterFile.updateRegister(operands[0], sum);
                    }
                }
            )
        );

        // unique ones

        // sayless: clone $v0 into EVERY register, no operands needed
        instructionList.add(
            new BasicInstruction("sayless",
                "say less: takes whatever's in $v0 and forces EVERY register to agree",
                BasicInstructionFormat.R_FORMAT,

                "000000 00000 00000 00000 00000 000011",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {

                        int value = RegisterFile.getValue(2);

                        int regCount = RegisterFile.getRegisters().length;
                        for (int i = 0; i < regCount; i++) {
                            RegisterFile.updateRegister(i, value);
                        }

                        SystemIO.printString("sayless made EVERY register equal to $v0 = " + value + "\n");
                    }
                }
            )
        );




        // ig: random 32-bit value
        instructionList.add(
            new BasicInstruction("ig $t1",
                "literally taking a random guess: set $t1 to a random 32-bit value",
                BasicInstructionFormat.I_FORMAT,
                "010000 00000 fffff 0000000000000000",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        int randVal = new Random().nextInt();
                        RegisterFile.updateRegister(operands[0], randVal);
                    }
                }
            )
        );

        // ngl: print register value with a little commentary
        instructionList.add(
            new BasicInstruction("ngl $t1",
                "not gonna lie... prints the value of $t1 with some commentary",
                BasicInstructionFormat.I_FORMAT,
                "010001 00000 fffff 0000000000000000",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        int val = RegisterFile.getValue(operands[0]);
                        SystemIO.printString("ngl this value kinda " + val + "\n");
                    }
                }
            )
        );

        // lowkey: take the smaller one
        instructionList.add(
            new BasicInstruction("lowkey $t1,$t2,$t3",
                "lowkey picks the smaller vibe: set $t1 to min($t2, $t3)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 101011",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        int v1 = RegisterFile.getValue(operands[1]);
                        int v2 = RegisterFile.getValue(operands[2]);
                        int m = (v1 < v2) ? v1 : v2;
                        RegisterFile.updateRegister(operands[0], m);
                    }
                }
            )
        );

        // lowkenuinely: shuffle all registers, then put & print smallest value
        instructionList.add(
            new BasicInstruction("lowkenuinely",
                "lowkenuinely shuffles all registers, then prints it the smallest value.",
                BasicInstructionFormat.R_FORMAT,
                "000000 00000 00000 00000 00000 101011",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {

                        int regCount = RegisterFile.getRegisters().length;
                        int[] vals = new int[regCount];

                        // snapshot all register values
                        for (int i = 0; i < regCount; i++) {
                            vals[i] = RegisterFile.getValue(i);
                        }

                        java.util.Random r = new java.util.Random();
                        for (int i = regCount - 1; i > 0; i--) {
                            int j = r.nextInt(i + 1);
                            int tmp = vals[i];
                            vals[i] = vals[j];
                            vals[j] = tmp;
                        }

                        for (int i = 0; i < regCount; i++) {
                            RegisterFile.updateRegister(i, vals[i]);
                        }

                        int min = vals[0];
                        for (int i = 1; i < regCount; i++) {
                            if (vals[i] < min) min = vals[i];
                        }
                        SystemIO.printString("lowkenuinely found the smallest value: " + min + "\n");
                    }
                }
            )
        );


        // highkey: take the bigger one
        instructionList.add(
            new BasicInstruction("highkey $t1,$t2,$t3",
                "highkey picks the louder one: set $t1 to max($t2, $t3)",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 101100",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        int v1 = RegisterFile.getValue(operands[1]);
                        int v2 = RegisterFile.getValue(operands[2]);
                        int m = (v1 > v2) ? v1 : v2;
                        RegisterFile.updateRegister(operands[0], m);
                    }
                }
            )
        );

        // highkenuinely: randomize all registers, then print largest value
        instructionList.add(
            new BasicInstruction("highkenuinely",
                "highkenuinely radomize all registers, then display the biggest vibe",
                BasicInstructionFormat.R_FORMAT,
                "000000 00000 00000 00000 00000 101100",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {

                        int regCount = RegisterFile.getRegisters().length;
                        int[] vals = new int[regCount];

                        for (int i = 0; i < regCount; i++) {
                            vals[i] = RegisterFile.getValue(i);
                        }

                        for (int pass = 0; pass < 3; pass++) {
                            for (int i = 0; i < regCount; i++) {
                                int j = (int)(Math.random() * regCount);
                                int tmp = vals[i];
                                vals[i] = vals[j];
                                vals[j] = tmp;
                            }
                        }

                        for (int i = 0; i < regCount; i++) {
                            RegisterFile.updateRegister(i, vals[i]);
                        }

                        int max = vals[0];
                        for (int i = 1; i < regCount; i++) {
                            if (vals[i] > max) max = vals[i];
                        }

                        RegisterFile.updateRegister(2, max);
                        SystemIO.printString("highkenuinely found the biggest value: " + max + "\n");
                    }
                }
            )
        );



        // well: average of two registers
        instructionList.add(
            new BasicInstruction("well $t1,$t2,$t3",
                "well it gets the average: set $t1 to the average of $t2 and $t3",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 101110",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        int v1 = RegisterFile.getValue(operands[1]);
                        int v2 = RegisterFile.getValue(operands[2]);
                        int avg = (v1 + v2) / 2;
                        RegisterFile.updateRegister(operands[0], avg);
                    }
                }
            )
        );

        // damn: square with overflow-to-zero
        instructionList.add(
            new BasicInstruction("damn $t1,$t2",
                "damn: set $t1 to $t2 squared, or 0 if it overflows",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss 00000 fffff 00000 101111",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        int v = RegisterFile.getValue(operands[1]);
                        long temp = (long)v * (long)v;
                        if (temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE) {
                            RegisterFile.updateRegister(operands[0], 0);
                        } else {
                            RegisterFile.updateRegister(operands[0], (int)temp);
                        }
                    }
                }
            )
        );

        // ratio: safe divide with meme-y divide-by-zero handling
        instructionList.add(
            new BasicInstruction("ratio $t1,$t2,$t3",
                "someone gets ratioed: set $t1 to $t2 / $t3, or 0 and flame if $t3 is 0",
                BasicInstructionFormat.R_FORMAT,
                "000000 sssss ttttt fffff 00000 110000",
                new SimulationCode() {
                    public void simulate(ProgramStatement statement) throws ProcessingException {
                        int[] operands = statement.getOperands();
                        int num = RegisterFile.getValue(operands[1]);
                        int den = RegisterFile.getValue(operands[2]);
                        if (den == 0) {
                            RegisterFile.updateRegister(operands[0], 0);
                            SystemIO.printString("ratio + you fell off lmao\n");
                        } else {
                            RegisterFile.updateRegister(operands[0], num / den);
                        }
                    }
                }
            )
        );

    
    }
}
