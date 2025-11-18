%.dll: %.c
	cl /I $(JAVA_HOME)\include /I $(JAVA_HOME)\include\win32 /LD $< /Fe$@

clean:
	del *.dll *.lib *.obj *.exp

.PHONY: all clean
