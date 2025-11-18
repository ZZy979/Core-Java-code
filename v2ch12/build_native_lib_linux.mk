lib%.so: %.c
	gcc -fPIC -I $(JAVA_HOME)/include -I $(JAVA_HOME)/include/linux -shared -o $@ $<

clean:
	rm *.so

.PHONY: all clean
