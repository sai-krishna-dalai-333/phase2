# Information Retrieval Engine (IR_Project)

## Project Structure

IR_Project/
├── src/
│   ├── com/
│   │   ├── ir/
│   │   │   ├── ForwardIndex.java
│   │   │   ├── InvertedIndex.java
│   │   │   ├── TextParser.java
│   │   │   ├── Retriever.java
│   │   │   └── Main.java
├── bin/
│   ├── com/
│   │   ├── ir/
│   │   │   ├── ForwardIndex.class
│   │   │   ├── InvertedIndex.class
│   │   │   ├── TextParser.class
│   │   │   ├── Retriever.class
│   │   │   └── Main.class
├── data/
│   ├── input/
│   │   ├── document1.txt
│   │   ├── document2.txt
│   │   └── stopwordlist.txt
│   ├── output/
│   │   ├── forward_index.txt
│   │   └── inverted_index.txt
├── docs/
│   ├── Report.txt
│   └── Readme.md


## Execution Steps

1. **Compile Java Files**:
   ```sh
   navigate to the path : cd /phase2

   use compile command:   javac -d bin src/com/ir/*.java


2. Run the Main Class:

  use command :   java -cp bin com.ir.Main


Input Files
data/input/ft911_1.txt
data/input/ft911_2.txt
....
....
.....
data/input/stopwordlist.txt


Output Files
data/output/forward_index.txt
data/output/inverted_index.txt