Difference Between Python Interpretation and JIT:
Python (CPython, without JIT):
Interprets bytecode line-by-line.
Executes instructions one at a time. Each bytecode instruction is read, evaluated, and then executed by the Python Virtual Machine (PVM).
Example: When Python encounters a loop, it doesn't compile the loop ahead of time but interprets each bytecode instruction inside the loop on every iteration.
JIT (Java JVM, PyPy for Python):
Monitors hotspots in the code (like loops or frequently called functions).
Compiles those hotspots to machine code so the next time they are encountered, they can be executed directly by the CPU.
Example: A loop running 1000 times may be interpreted a few times, but when the JIT detects it as a hotspot, it will compile the loop into machine code. The next time, the loop runs at full CPU speed, bypassing the need for interpretation.
