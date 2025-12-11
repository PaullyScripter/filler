


        # set all registers with 5
        uh $v0, $zero, 5
        sayless

        uh $t0,$t0,-100      # now $t0 = -95

        btw $t1,$t0,20       # $t1 = -75

        um $t2,$t0,$t1       # $t2 = -170

        # like: random(0-$t3) + $t2
        like $t3,$t2,$t1     # random behavior

        bruh $t4,$t1,$t0     # $t4 = 20

        damn $t5,$t4         # $t5 = 400

        # ig: random 32-bit
        ig $t6

        # ratio: safe divide
        # set registers to 4 using sayless
        uh $v0, $zero, 4        
        sayless
        ratio $t7,$t4,$t5    # 20 / 400 → 0

        # nah: AND
        nah $s0,$t4,$t5

        # orlike: OR
        orlike $s1,$t4,$t5

        # yeno: XOR
        yeno $s2,$t4,$t5

        # uknow: force a register to 0 with a message
        uknow $s3
