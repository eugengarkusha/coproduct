This repo contains the sample implementation of Coproduct with constant runtime overhead.
Coproduct is represented  by a single container class having an 'index' and a 'value',  labeled by the 'Shape' which defines the union type.

Features:
-no Inr/Inl boxing
-minimized runtime overhead for ops typeclasses (TODO: implement Nat spawning using macros for greater benefit of this point)

Approximate performance benefits(calculated using provided performance test, benchmarking the coproduct creation operation).
- memory: consumes > 2x less memory
- cpu: performes ~2x faster
 TODO: unprecise, need more (and better) performance tests.

It is created for demo purpose (proof of concept).