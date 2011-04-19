.DELETE_ON_ERROR:

.PHONY: test clean

test: euler
	./euler

euler: euler.hs
	ghc --make -O2 -fglasgow-exts euler.hs

clean:
	rm -f euler euler.hi euler.o

realclean:
	svn status --no-ignore | grep '^[?I]' | cut -c 9- | tr '\n' '\0' | xargs -0 rm -rf
