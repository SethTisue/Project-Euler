.DELETE_ON_ERROR:

.PHONY: test clean

test: euler
	./euler

euler: euler.hs
	ghc --make -O2 euler.hs

clean:
	rm -f euler euler.hi euler.o

realclean:
	git clean -fdX
