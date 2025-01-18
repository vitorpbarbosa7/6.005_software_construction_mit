The **million-dollar question** posed by the **Clay Mathematics Institute** in the Millennium Prize Problems—"Does \( P = NP \)?"—is specifically about the class of **NP-complete** problems.

---

### **Why NP-complete?**
1. **NP-complete Problems:** 
   - These are the hardest problems within NP. 
   - If we can find a polynomial-time solution to one NP-complete problem, we can reduce any other NP problem to it in polynomial time, thus proving \( P = NP \).
   - Examples include **SAT**, **3-SAT**, **Traveling Salesman Problem (TSP)** (decision version), and **Graph Coloring**.

2. **NP-hard Problems:**
   - These are at least as hard as NP-complete problems but are not necessarily in NP.
   - NP-hard problems may not even have solutions verifiable in polynomial time. For example, problems like the **Halting Problem** are NP-hard but not in NP because they are undecidable.

---

### **Distinction Between NP-complete and NP-hard**
The \( P = NP \) question focuses on NP-complete problems because they are a subset of NP, meaning their solutions can be **verified** in polynomial time. If we show one NP-complete problem can be solved in polynomial time, it means all NP problems can be solved in polynomial time, resolving \( P = NP \).

However, NP-hard problems are more general and include problems outside NP, so they are not the focus of the \( P = NP \) question.

---

### **In Short**
The question \( P = NP \) is about **NP-complete** problems, not NP-hard problems in general.
