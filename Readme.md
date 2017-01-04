# Constant overhead coproduct
This repo contains the sample implementation of Coproduct with constant runtime overhead.
Coproduct is represented  by a single container class having an 'index' and a 'value',  labeled by the 'Shape' which defines the union type.

Features:  
-no Inr/Inl boxing  
-minimized runtime overhead for ops typeclasses (TODO: implement Nat spawning using macros for greater benefit of this point)

Performance details:  
-creates one object per instance (hence consumes less memory and runs faster)  
TODO: imprecise, need more (and better) performance tests.  
-jvisualvm monitor graph(taken while running the Coproduct creation benchmark included in this repo):

shapeless existing impl: ![alt tag](https://lh3.googleusercontent.com/aRBgp6D1R6gjzjmXPOsUhyqkkcaw8JARrkiDLuVaw7a68k56D6oJbtPTkg5r1ta7YbAcm6BrArGZ6_kbz1lNniojyaHX_UsOWad_RXQ8d06Nx1pqfXE2M0aOK0sAbDL49f_QTslOU19c9jKrxTyYhKxQ5GEFUaihV19gCgUO5dAwQkGZScsaUHgMGWwD-NVZ3wq3yZAbLsmLdQKhiLQ8F5oz4VL1FmBC1xUfBh7l3xlwzI0DmkBlgmnTFiDqaItljQGMYiF99KyiaQ6SGFJNs15SKrZ52I1y-dY3c_CiRfnIm3o-uym-qd9OKWyAEuXKq0ztEollaHpxd8m7YPLxBSPLp8zfKd62gGPYN-r4HopiWYYPRk_dy64DVkF0nr-1nEqWDNK2odNUaIus_qjJUUexG6M0YtuCvXw5IA2M5t9Q-IymeiPdm7mAKzd4sc92uCBmHK0MToGqrvKxUpmvvmzG4x8h-GF-dTGRBysh5BFZgiWADgoGxhPar2Vz9f_27mkFuI33L9zTYT0HGln_VK-y_HruU6EyLHH_1GLXxAUZ6DxuJX4o0GTRyRqKCCT1iftvTd0HV2itNxlkq63TJ6OmGOeD3ihHx7rG_0PqIVNrwrJaz68fa0xpmMx1zunBoPtAe5IIo8ZYOldxmweNq_tnKa3JUzjS03Lf7VWOrA=w2300-h687-no)  

proposed impl: ![alt tag](https://lh3.googleusercontent.com/k-nVlHCDtTaUaf7kovsnkJktXXvVV2U9lNjD-IaCxFsYmIECPAcIIGeP48jb5ntO6ABwgPMPv-XImoFxcVksq_RJKgmyjsP-RraxJYzxLV801AH_fNkHIgYt3v97I8Ft77q-dK0Jx25Ljv4j3RAmavBEchzlghJR5TvySteW_y6BekIAW70H3UzepXbxe3DD6JlWx5oEzmOPU3VWPBwEHbdI3jo_T6VeO3S7NfXP9KAYOIm9wDeyH32WODOuM0EHSp2FnVL5bQ0_bIm0tIpcvfR9nJEowlY2W83vPo3wbm_4pjQ4dzCR8Yw9L9FMV2fUcH3CHFM8C1_5v8zMKEUXc4Llfu-QmNBOQYq_RUyHvRrEJptJUuoj08Ubv4mlYn1aF7JFj4AU0NQp_xYLt6ClYqZI24eWUqk3N3--Zoac3IEbDxeqkNTJjaHbCuKqW3Uam8COXDWMsNLd4v6tWZfwc-LoagZ8bd9LZWTCC7tkWr71GQBccKpuo-vzawfpsFGzNRFPfF7GkTpFux2GItIpQ1sEnIM89sWFhpKYjR9GNLEVeu93qWJP0QRD8kPdGsgRKf6Q2nRq003dKFqr48xBln5Ho341SxwxN3ZYu-OWjbsfc5Wt6VHCwwIMT4NX14tnFU0_qBTeVK19MeACwvd7PAVUsQ0I4CpR9dpUyHbLDQ=w2002-h640-no)



Project is created for demo purpose (proof of concept).
