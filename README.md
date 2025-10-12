# AI_Assignments_2025-26

> Collection of AI assignments (Java) for the 2025–26 AI course SEM 1

---

## Project overview

This repository contains practical assignments for classical AI topics implemented in **Java**. The assignments are organized into folders (`ass1`, `ass2`, ..., `ass9`) and cover search algorithms, reasoning (forward/backward chaining), constraint satisfaction (8-Queens), genetic algorithms, swarm intelligence, and a simple chatbot project.

**Primary goals:**

* Provide clear, working Java implementations for core AI algorithms.
* Offer readable code and runnable examples for learning and extension.

---

## Repository structure

```
AI_Assignments_2025-26/
├─ ass1/          # Assignment 1 (e.g., basic search problems)
├─ ass2/          # Assignment 2 (e.g., informed search)
├─ ass3/          # Assignment 3 (e.g., local search / hill climbing)
├─ ass4/          # Assignment 4 (e.g., constraint satisfaction - 8-Queens)
├─ ass5/          # Assignment 5 (e.g., genetic algorithms)
├─ ass6/          # Assignment 6 (e.g., swarm intelligence / ACO)
├─ ass7/          # Assignment 7 (e.g., simulated annealing / optimization)
├─ ass8/          # Assignment 8 (e.g., planning / heuristics)
├─ ass9/          # Assignment 9 (e.g., chatbot / rule-based system)
└─ README.md      # This file
```

> If a directory contains multiple classes, look for the `Main` or similarly named class to execute the example.

---

## Key implementations (high level)

* **8-Queens (Constraint Satisfaction)**: Backtracking search to place 8 queens so none attack each other.
* **Forward Chaining**: Data-driven inference engine using production rules and a working memory of facts.
* **Backward Chaining**: Goal-driven inference engine that attempts to prove a goal by recursively proving prerequisites.
* **Chatbot**: A simple FAQ-style chatbot using rule matching and/or chaining for reasoning.
* **GA / ACO / Local Search**: Example implementations demonstrating evolutionary and swarm approaches.

---

## Requirements

* Java JDK 8 or higher
* (Optional) An IDE such as IntelliJ IDEA, Eclipse, or VS Code with Java support.

---

## How to compile & run

From the repository root, use the terminal / command line.

1. Compile all Java files (unix/macOS / Windows using Git Bash / PowerShell):

```bash
# compile everything recursively
javac $(find . -name "*.java")
```

> On Windows without `find`, compile per-folder or use an IDE.

2. Run a particular example (replace `path.to.MainClass` with the actual package + class):

```bash
java path.to.MainClass
```

Examples:

* To run the 8-Queens example, navigate to `ass4/` and run the class that contains `main` (commonly `EightQueens`).
* To run the chatbot, navigate to `ass9/` and run the chatbot `main` class.

---

## Notes & recommendations

* Each assignment folder should contain a short `README.txt` or comment header describing how to run that assignment and which class contains `main()`.
* Rename the `main` classes or add a driver `Main.java` in each assignment folder for consistent startup.
* Add inline comments and simple test inputs so graders/readers can quickly validate outputs.

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feat/assignment-X`)
3. Commit your changes (`git commit -m "Add explanation / improve README for assX"`)
4. Push to the branch (`git push origin feat/assignment-X`)
5. Open a Pull Request

Please keep code style consistent and add comments where logic is non-trivial.

---


## Author

Shivraj Darekar — `shivraj2805` on GitHub


