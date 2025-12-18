# Necromancy Assembly Language

A dark-magic themed custom assembly language for MARS MIPS Simulator. Because who says assembly can't be spooky?

## What is This Project?

Think of this like a "skin" for assembly language. You know how in MIPS you write `add $t0, $t1, $t2` to add two numbers? This project lets you write `summon $t0, $t1, $t2` instead. The computer does the exact same thing, but with way cooler instruction names.

**What's MARS?**
MARS (MIPS Assembler and Runtime Simulator) is a program that lets you write and test MIPS assembly code on your computer without needing actual MIPS hardware. It's basically a virtual MIPS computer.

**What's a Custom Assembly Language?**
MARS has a feature that lets you create your own instruction names. This project uses that feature to replace standard MIPS instructions with necromancy-themed ones. Under the hood, it's still regular MIPS - just with spooky names.

**Why would anyone do this?**
1. It makes learning assembly more fun
2. It helps you understand that assembly instructions are just human-readable labels for binary operations
3. Honestly, saying "I summoned souls" sounds way better than "I added integers"

## What Instructions Are Implemented?

This custom language has **20 instructions** total. Here's what each one does:

### Basic Math Operations (Like Normal MIPS)
These work exactly like the standard MIPS arithmetic instructions:

| Necromancy Instruction | MIPS Equivalent | What It Does | Example |
|------------------------|-----------------|--------------|---------|
| `summon $t1, $t2, $t3` | `add $t1, $t2, $t3` | Adds $t2 + $t3, stores in $t1 | If $t2=5 and $t3=3, then $t1=8 |
| `drain $t1, $t2, $t3` | `sub $t1, $t2, $t3` | Subtracts $t2 - $t3, stores in $t1 | If $t2=10 and $t3=4, then $t1=6 |
| `clone $t1, $t2, $t3` | `mult $t2, $t3` + `mflo $t1` | Multiplies $t2 × $t3, stores in $t1 | If $t2=4 and $t3=5, then $t1=20 |
| `splitsoul $t1, $t2, $t3` | `div $t2, $t3` + `mflo $t1` | Divides $t2 ÷ $t3, stores in $t1 | If $t2=15 and $t3=3, then $t1=5 |

### Bitwise Operations (For Binary Manipulation)
These let you mess with individual bits in numbers:

| Necromancy Instruction | MIPS Equivalent | What It Does | When You'd Use It |
|------------------------|-----------------|--------------|-------------------|
| `bind $t1, $t2, $t3` | `and $t1, $t2, $t3` | Bitwise AND | Checking if specific bits are set |
| `merge $t1, $t2, $t3` | `or $t1, $t2, $t3` | Bitwise OR | Combining bit flags |
| `corrupt $t1, $t2, $t3` | `xor $t1, $t2, $t3` | Bitwise XOR | Flipping bits, simple encryption |

### Control Flow (Making Decisions)
These let your program make choices and loop:

| Necromancy Instruction | MIPS Equivalent | What It Does | Example Use |
|------------------------|-----------------|--------------|-------------|
| `haunt $t1, $t2, label` | `beq $t1, $t2, label` | Jump to label if $t1 equals $t2 | Exit a loop when counter reaches target |
| `curse $t1, $t2, label` | `bne $t1, $t2, label` | Jump to label if $t1 doesn't equal $t2 | Keep looping while condition is true |

### Memory Operations (Saving & Loading Data)
These move data between registers and RAM:

| Necromancy Instruction | MIPS Equivalent | What It Does | Explanation |
|------------------------|-----------------|--------------|-------------|
| `banish $t1, 100` | `addi $t1, $zero, 100` | Put the number 100 directly into $t1 | "Immediate" load - puts a constant in a register |
| `phylactery $t1, 0($t2)` | `sw $t1, 0($t2)` | Store value from $t1 into memory at address in $t2 | Saves data to RAM |
| `unearth $t1, 0($t2)` | `lw $t1, 0($t2)` | Load value from memory at $t2 into $t1 | Retrieves saved data from RAM |

### Special Necromancy Instructions (Unique to This Language!)
These are custom instructions you won't find in normal MIPS:

| Instruction | What It Does | Example |
|-------------|--------------|---------|
| `resurrect $t1, $t2` | Copies absolute value of $t2 to $t1 | If $t2=-5, then $t1=5 |
| `decay $t1` | Cuts $t1 in half | If $t1=100, becomes 50 |
| `possess $t1, $t2` | Swaps values of $t1 and $t2 | If $t1=3 and $t2=7, they swap |
| `ritual $t1` | Adds ALL $t0-$t7 registers together into $t1 | Quick way to sum multiple values |
| `vampire $t1, $t2` | $t1 steals half of $t2's value, $t2 loses that half | If $t1=10 and $t2=20, then $t1=20 and $t2=10 |
| `lich $t1` | Sets $t1 to maximum possible integer (2,147,483,647) | Gives "immortal" power |
| `necromancy $t1, $t2` | If $t1 is zero, copy $t2 into it | Conditional resurrection |
| `soulread label` | Prints a string from memory at label | Like syscall 4 for printing strings |

## How to Run/Test in MARS (Step-by-Step Guide)

### Prerequisites
You need MARS installed on your computer. This project includes `Mars.jar` - that's the MARS simulator.

### Step 1: Understanding the File Structure

Before we start, here's what's in this project:
```
Necromancy-Assembly-Language/
├── Mars.jar                                    ← The MARS simulator program
└── mars/
    └── mips/
        └── instructions/
            └── customlangs/
                ├── NecromancyAssembly.java     ← Source code for our custom language
                └── NecromancyAssembly.jar      ← Compiled version (this is what MARS uses)
```

The `.jar` file in the `customlangs` folder is the important one - MARS automatically detects it when you run the simulator.

### Step 2: Launch MARS

Open your terminal/command prompt and navigate to this project folder:

```bash
# On Mac/Linux:
cd /path/to/Necromancy-Assembly-Language
java -jar Mars.jar

# On Windows:
cd C:\path\to\Necromancy-Assembly-Language
java -jar Mars.jar
```

**What this does:** Launches the MARS graphical interface. MARS will automatically scan the `mars/mips/instructions/customlangs/` folder and find our Necromancy Assembly language.

### Step 3: Enable Necromancy Assembly

Once MARS opens:

1. Look at the top menu bar
2. Click **Settings** → **Custom Assembly Language**
3. You should see a dropdown menu with "Necromancy Assembly" as an option
4. Select it and click **Apply** or **OK**

**What this does:** Tells MARS to use our custom instruction names instead of standard MIPS.

### Step 4: Write Your First Necromancy Program

In MARS, click **File** → **New** to create a new file. Type this simple program:

```assembly
.data
    # This section is for storing data (like variables)
    message: .asciiz "The ritual is complete!\n"

.text
    # This is where your actual code goes
    .globl main

main:
    # Let's add two numbers using necromancy
    banish $t0, 10              # Put 10 into register $t0
    banish $t1, 20              # Put 20 into register $t1
    summon $t2, $t0, $t1        # Add them: $t2 = 10 + 20 = 30

    # Print our message
    soulread message            # Print the string we defined above

    # Exit the program
    banish $v0, 10              # Syscall code 10 = exit
    syscall                     # Make the syscall
```

**Save this file** as `test.asm` somewhere on your computer.

### Step 5: Assemble Your Code

1. With your file open in MARS, click **Run** → **Assemble** (or press **F3**)
2. Look at the bottom of the MARS window for the "Messages" panel
3. If it says "Assemble: assembly succeeded" - great! If not, check for typos.

**What this does:** Converts your human-readable necromancy code into binary machine code that the simulated MIPS processor can understand.

### Step 6: Run Your Program

1. Click **Run** → **Go** (or press **F5**)
2. Watch the **Registers** panel on the right - you'll see $t0, $t1, and $t2 change values!
3. Check the **Run I/O** panel at the bottom - you should see "The ritual is complete!" printed

**What's happening:**
- $t0 gets value 10
- $t1 gets value 20
- $t2 gets 30 (10+20)
- The message prints
- Program exits

### Step 7: Debug and Observe (Optional but Cool!)

Want to see each instruction execute one at a time?

1. Assemble your code (F3)
2. Click **Run** → **Step** (or press **F7**) repeatedly
3. Watch each instruction execute one by one
4. Observe how register values change in real-time

This is super helpful for understanding what each instruction actually does!

## More Example Programs

### Example 1: Simple Calculator
**Goal:** Add two numbers and store the result

```assembly
.text
.globl main

main:
    banish $t0, 15           # First number = 15
    banish $t1, 27           # Second number = 27
    summon $t2, $t0, $t1     # Add them: $t2 = 42

    # Exit
    banish $v0, 10
    syscall
```

**After running:** Check the Registers panel - $t2 should contain 42

---

### Example 2: Loop Counter (Count from 0 to 5)
**Goal:** Learn how loops work with `haunt` and `curse`

```assembly
.text
.globl main

main:
    banish $t0, 0        # Counter starts at 0
    banish $t1, 5        # We want to count to 5

loop:
    banish $t2, 1        # Increment amount
    summon $t0, $t0, $t2 # counter = counter + 1
    curse $t0, $t1, loop # If counter ≠ 5, keep looping

    # When we get here, $t0 should equal 5
    banish $v0, 10
    syscall
```

**How it works:**
1. Start with $t0 = 0
2. Add 1 to $t0 (now $t0 = 1)
3. Check if $t0 equals 5. It doesn't, so jump back to `loop:`
4. Repeat until $t0 = 5
5. Exit

**Step through this with F7 to watch the loop happen!**

---

### Example 3: Using Memory (Phylactery & Unearth)
**Goal:** Store a value in RAM and retrieve it

```assembly
.data
    storage: .word 0     # Reserve 4 bytes in memory, initialize to 0

.text
.globl main

main:
    banish $t0, 666      # Load the number 666

    # Store it in memory
    phylactery $t0, storage  # Save $t0 to the memory location 'storage'

    # Clear the register
    banish $t0, 0        # $t0 is now 0

    # Retrieve it from memory
    unearth $t1, storage # Load from 'storage' into $t1

    # Now $t1 should contain 666!
    banish $v0, 10
    syscall
```

**What's happening:**
- `storage` is a label for a memory address
- `phylactery` stores the value TO memory
- `unearth` loads the value FROM memory
- This is like saving/loading variables

---

### Example 4: The Vampire Drains Life
**Goal:** Demonstrate the unique `vampire` instruction

```assembly
.text
.globl main

main:
    banish $t0, 50       # Vampire starts with 50 health
    banish $t1, 100      # Victim has 100 health

    vampire $t0, $t1     # Vampire drains half of victim's health

    # Result:
    # $t0 (vampire) now has 50 + 50 = 100 health
    # $t1 (victim) now has 100 - 50 = 50 health

    banish $v0, 10
    syscall
```

**After running:**
- $t0 should be 100 (gained 50)
- $t1 should be 50 (lost 50)

---

### Example 5: The Dark Ritual (Sum All Registers)
**Goal:** Use `ritual` to sum multiple values

```assembly
.text
.globl main

main:
    # Put some values in the $t registers
    banish $t0, 1
    banish $t1, 2
    banish $t2, 3
    banish $t3, 4
    banish $t4, 5
    banish $t5, 6
    banish $t6, 7
    banish $t7, 8

    # Perform the ritual - sum them all!
    ritual $s0          # $s0 = 1+2+3+4+5+6+7+8 = 36

    banish $v0, 10
    syscall
```

**After running:** $s0 should contain 36 (the sum of 1 through 8)

---

### Example 6: Print a Spooky Message
**Goal:** Use `soulread` to print strings

```assembly
.data
    greeting: .asciiz "Welcome to the crypt...\n"
    farewell: .asciiz "The darkness consumes you.\n"

.text
.globl main

main:
    soulread greeting    # Print first message
    soulread farewell    # Print second message

    banish $v0, 10
    syscall
```

**Output in Run I/O panel:**
```
Welcome to the crypt...
The darkness consumes you.
```

## Understanding the Binary (For the Curious)

If you're wondering how this actually works at the machine level:

Every instruction in assembly gets converted to a 32-bit binary number. For example:
- `summon $t0, $t1, $t2` becomes `000000 01001 01010 01000 00000 100001`
- This is identical to MIPS `add` instruction - just with a different name!

The opcodes (first 6 bits) and function codes (last 6 bits) tell the CPU what operation to perform. Our custom instructions use the same codes as standard MIPS, which is why they work in MARS.

**Instruction Format Types:**
- **R-Format:** Used for register-to-register operations (summon, drain, clone, etc.)
- **I-Format:** Used for immediate values and memory (banish, phylactery, unearth)
- **J-Format:** Jump instructions (we don't use these, but MARS supports them)

---

## Common Mistakes & Troubleshooting

### "Error: instruction not recognized"
**Problem:** MARS doesn't know what `summon` means

**Solutions:**
1. Did you enable the custom language? Go to Settings → Custom Assembly Language → Select "Necromancy Assembly"
2. Check spelling - it's `summon`, not `summons`
3. Make sure you're using the right registers ($t0-$t9, $s0-$s7, $a0-$a3, $v0-$v1)

---

### "Runtime exception at 0x00400000: address out of range"
**Problem:** You tried to access memory that doesn't exist

**Solutions:**
1. When using `phylactery` or `unearth`, make sure you've declared the memory location in `.data`
2. Check that your base register ($t2 in `phylactery $t0, 0($t2)`) contains a valid address

**Example of correct memory usage:**
```assembly
.data
    myvar: .word 0    # Declare it first!

.text
    banish $t0, 100
    phylactery $t0, myvar    # Now this works
```

---

### "Cannot split soul into zero fragments"
**Problem:** You tried to divide by zero with `splitsoul`

**Solution:** Check that your divisor register isn't zero
```assembly
banish $t0, 10
banish $t1, 0
splitsoul $t2, $t0, $t1    # ERROR! Can't divide by 0

# Fix:
banish $t1, 2              # Use a non-zero divisor
splitsoul $t2, $t0, $t1    # Now $t2 = 10/2 = 5
```

---

### "The custom language doesn't appear in Settings"
**Problem:** MARS can't find the NecromancyAssembly.jar file

**Solutions:**
1. Make sure you're running `java -jar Mars.jar` from the **project root directory**
2. Verify the file structure:
   ```
   Your-Location/
   ├── Mars.jar
   └── mars/
       └── mips/
           └── instructions/
               └── customlangs/
                   └── NecromancyAssembly.jar  ← Must be here!
   ```
3. If you moved Mars.jar somewhere else, copy the entire `mars/` folder along with it

---

### Program runs but nothing happens
**Problem:** Forgot to exit properly

**Solution:** Always end with:
```assembly
banish $v0, 10    # Syscall 10 = exit
syscall           # Actually make the system call
```

---

## Quick Reference Card

Copy this for easy reference while coding:

| Need to... | Use this instruction | Example |
|------------|---------------------|---------|
| Put a number in a register | `banish $t0, 42` | Load 42 into $t0 |
| Add two numbers | `summon $t1, $t2, $t3` | $t1 = $t2 + $t3 |
| Subtract | `drain $t1, $t2, $t3` | $t1 = $t2 - $t3 |
| Multiply | `clone $t1, $t2, $t3` | $t1 = $t2 × $t3 |
| Divide | `splitsoul $t1, $t2, $t3` | $t1 = $t2 ÷ $t3 |
| Save to memory | `phylactery $t0, label` | Store $t0 at label |
| Load from memory | `unearth $t0, label` | Load from label to $t0 |
| Print a string | `soulread label` | Print string at label |
| Loop if not equal | `curse $t0, $t1, loop` | Jump to loop if $t0 ≠ $t1 |
| Jump if equal | `haunt $t0, $t1, label` | Jump to label if $t0 = $t1 |
| Swap two registers | `possess $t0, $t1` | Exchange $t0 ↔ $t1 |
| Exit program | `banish $v0, 10` + `syscall` | Terminates program |

---

## Why Did I Make This?

Because learning assembly language is genuinely hard. Your brain has to think in a completely different way - managing registers manually, dealing with memory addresses, understanding how the CPU actually works. It's a lot!

So why not make it fun? When you write `vampire $t0, $t1`, you're doing the exact same thing as some more boring instruction - but now you're picturing a vampire draining life force instead of just "moving bits around." It makes the concepts more memorable and way more entertaining.

Plus, you can tell your friends you spent the weekend "performing dark rituals in assembly language" which is objectively cooler than "doing homework."




