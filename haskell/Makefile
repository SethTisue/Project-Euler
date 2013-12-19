.DELETE_ON_ERROR:

.PHONY: test

test: euler
	./euler

euler euler.o euler.hi: euler.hs
	ghc --make -O2 euler.hs
