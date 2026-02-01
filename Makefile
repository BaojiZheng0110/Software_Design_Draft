JAVAC = javac
JAVA  = java
SRC   = Wordle.java
MAIN  = Wordle
OUT   = bin

.PHONY: all run clean

all: $(OUT)/$(MAIN).class

$(OUT)/$(MAIN).class: $(SRC)
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(SRC)

run: all
	$(JAVA) -cp $(OUT) $(MAIN)

clean:
	rm -rf $(OUT)
