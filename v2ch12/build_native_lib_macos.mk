lib%.dylib: %.c
	gcc -dynamiclib -I $(JAVA_HOME)/include -I $(JAVA_HOME)/include/darwin -o $@ $<

clean:
	rm *.dylib

.PHONY: all clean
