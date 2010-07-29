.DELETE_ON_ERROR:

.PHONY: test clean

test: euler
	./euler

clean:
	rm -f euler euler.hi euler.o

euler: euler.hs
	ghc --make -O2 -fglasgow-exts euler.hs
