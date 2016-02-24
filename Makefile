# Java Compiler
JC = javac

# Output Directory
ODIR = classes
classpath = classes:.

# Compiler Flags
JFLAGS = -d $(ODIR) -classpath $(classpath)

# Main class
MAIN = Library.class

SRC= model/EntityBase.java \
	 model/Book.java	model/BookCollection.java \
	 model/Patron.java	model/PatronCollection.java \
	 model/Transaction.java model/TransactionCollection.java
CLASSFILES = $(patsubst %.java, $(ODIR)/%.class, $(SRC))

all: $(CLASSFILES) $(MAIN)

$(MAIN): $(MAIN:.class=.java)
	$(JC) -classpath $(classpath) $<

$(ODIR)/%.class: %.java
	$(JC) $(JFLAGS) $<

clean:
	rm -f *.class ./$(ODIR)/*/*.class 
