**This program accepts text files as input and filters the content into output files by data type.**

# Requirements
### JDK 17.0.10
### Maven 3.9.2

# Dependencies
### commons-cli 1.8.0
<!-- https://mvnrepository.com/artifact/commons-cli/commons-cli -->
        <dependency>
            <groupId>commons-cli</groupId>
            <artifactId>commons-cli</artifactId>
            <version>1.8.0</version>
        </dependency>
### projectLombok 1.18.34
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.34</version>
            <scope>provided</scope>
        </dependency>

# Usage

You can use the **-o** option to specify the path for the results. 

Option **-p** specifies the prefix of output file names. For example **-o /some/path -p result_** sets the output to
files **/some/path/result_integers.txt**, **/some/path/result_strings.txt** and etc. 

During the data filtering process, statistics are collected for each type of data.
Statistics are supported in two types: short and full. Selecting statistics
done with the **-s** and **-f** options, respectively. Brief statistics contain only
number of elements written to outgoing files. Full statistics for numbers
additionally contains the minimum and maximum values, sum and average.
Complete statistics for rows, in addition to their number, also contains the size of the row itself.
the shortest line and the longest. Statistics for each data type are displayed in the console.

Example of running the utility:
**java -jar ShiftTest.jar -s -a -p sample- in1.txt in2.txt**
