

        # Initialize all registers to 10
        uh $v0, $zero, 10
        sayless

        
        lowkey $t0,$t1,$t2#  lowkey: min
        
        highkey $t1,$t2,$t3 # highkey: max

        well $t2,$t0,$t1 # well: average

        
        frfr $t3,$t0,$t1 # frfr: set-on-less-than

        ngl $t2 # ngl: print value of a register

	    uh $v0, $zero 42
        sayless

        ngl $t5

        lowkenuinely # lowkenuinely: shuffle all registers → print smallest value

        highkenuinely # highkenuinely: shuffle all registers → print smallest value

