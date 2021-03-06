EESchema Schematic File Version 2
LIBS:ic-digital
LIBS:power
LIBS:device
LIBS:transistors
LIBS:conn
LIBS:linear
LIBS:regul
LIBS:74xx
LIBS:cmos4000
LIBS:adc-dac
LIBS:memory
LIBS:xilinx
LIBS:microcontrollers
LIBS:dsp
LIBS:microchip
LIBS:analog_switches
LIBS:motorola
LIBS:texas
LIBS:intel
LIBS:audio
LIBS:interface
LIBS:digital-audio
LIBS:philips
LIBS:display
LIBS:cypress
LIBS:siliconi
LIBS:opto
LIBS:atmel
LIBS:contrib
LIBS:valves
LIBS:matrix-dev-board-cache
EELAYER 25 0
EELAYER END
$Descr USLetter 11000 8500
encoding utf-8
Sheet 1 1
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L IS31FL3733 U1
U 1 1 5AA9A71C
P 5900 4150
F 0 "U1" H 5900 5000 60  0000 C CNN
F 1 "IS31FL3733" H 5900 4850 60  0000 C CNN
F 2 "Housings_DFN_QFN:QFN-48-1EP_6x6mm_Pitch0.4mm_ThermalVias" H 5900 4850 60  0001 C CNN
F 3 "http://www.issi.com/WW/pdf/31FL3733.pdf" H 5900 4850 60  0001 C CNN
F 4 "706-1509-1-ND" H 5900 4150 60  0001 C CNN "DKPN"
	1    5900 4150
	1    0    0    -1  
$EndComp
$Comp
L CONN_01X12 J4
U 1 1 5AA9B2E6
P 7250 4650
F 0 "J4" H 7250 5300 50  0000 C CNN
F 1 "CONN_01X12" V 7350 4650 50  0000 C CNN
F 2 "Pin_Headers:Pin_Header_Angled_1x12_Pitch1.27mm" H 7250 4650 50  0001 C CNN
F 3 "" H 7250 4650 50  0001 C CNN
	1    7250 4650
	1    0    0    1   
$EndComp
$Comp
L CONN_01X16 J3
U 1 1 5AA9B36B
P 7250 3150
F 0 "J3" H 7250 4000 50  0000 C CNN
F 1 "CONN_01X16" V 7350 3150 50  0000 C CNN
F 2 "Pin_Headers:Pin_Header_Angled_1x16_Pitch1.27mm" H 7250 3150 50  0001 C CNN
F 3 "" H 7250 3150 50  0001 C CNN
	1    7250 3150
	1    0    0    1   
$EndComp
Wire Wire Line
	6950 2400 7050 2400
Wire Wire Line
	6950 2500 7050 2500
Wire Wire Line
	7050 2600 6950 2600
Wire Wire Line
	6950 2700 7050 2700
Wire Wire Line
	7050 2800 6950 2800
Wire Wire Line
	6950 2900 7050 2900
Wire Wire Line
	7050 3000 6950 3000
Wire Wire Line
	6950 3100 7050 3100
Wire Wire Line
	6950 3200 7050 3200
Wire Wire Line
	6950 3300 7050 3300
Wire Wire Line
	6950 3400 7050 3400
Wire Wire Line
	6950 3500 7050 3500
Wire Wire Line
	6950 3600 7050 3600
Wire Wire Line
	6950 3700 7050 3700
Wire Wire Line
	6950 3800 7050 3800
Wire Wire Line
	6950 3900 7050 3900
Wire Wire Line
	6950 4100 7050 4100
Wire Wire Line
	6950 4200 7050 4200
Wire Wire Line
	6950 4300 7050 4300
Wire Wire Line
	6950 4400 7050 4400
Wire Wire Line
	6950 4500 7050 4500
Wire Wire Line
	6950 4600 7050 4600
Wire Wire Line
	6950 4700 7050 4700
Wire Wire Line
	6950 4800 7050 4800
Wire Wire Line
	6950 4900 7050 4900
Wire Wire Line
	6950 5000 7050 5000
Wire Wire Line
	6950 5100 7050 5100
Wire Wire Line
	6950 5200 7050 5200
$Comp
L R R9
U 1 1 5AA9C11D
P 4700 5150
F 0 "R9" V 4780 5150 50  0000 C CNN
F 1 "0" V 4700 5150 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 4630 5150 50  0001 C CNN
F 3 "" H 4700 5150 50  0001 C CNN
	1    4700 5150
	0    1    1    0   
$EndComp
$Comp
L R R10
U 1 1 5AA9C30E
P 4700 5500
F 0 "R10" V 4780 5500 50  0000 C CNN
F 1 "0" V 4700 5500 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 4630 5500 50  0001 C CNN
F 3 "" H 4700 5500 50  0001 C CNN
	1    4700 5500
	0    1    1    0   
$EndComp
Wire Wire Line
	4900 5150 4850 5150
Wire Wire Line
	4850 5500 4900 5500
$Comp
L GND #PWR01
U 1 1 5AA9CCBA
P 4500 5600
F 0 "#PWR01" H 4500 5350 50  0001 C CNN
F 1 "GND" H 4500 5450 50  0000 C CNN
F 2 "" H 4500 5600 50  0001 C CNN
F 3 "" H 4500 5600 50  0001 C CNN
	1    4500 5600
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR02
U 1 1 5AA9CE00
P 4500 5250
F 0 "#PWR02" H 4500 5000 50  0001 C CNN
F 1 "GND" H 4500 5100 50  0000 C CNN
F 2 "" H 4500 5250 50  0001 C CNN
F 3 "" H 4500 5250 50  0001 C CNN
	1    4500 5250
	1    0    0    -1  
$EndComp
Wire Wire Line
	4500 5250 4500 5150
Wire Wire Line
	4500 5600 4500 5500
$Comp
L +3V3 #PWR03
U 1 1 5AA9D8F8
P 4750 2200
F 0 "#PWR03" H 4750 2050 50  0001 C CNN
F 1 "+3V3" H 4750 2340 50  0000 C CNN
F 2 "" H 4750 2200 50  0001 C CNN
F 3 "" H 4750 2200 50  0001 C CNN
	1    4750 2200
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR04
U 1 1 5AA9DBD7
P 3950 2400
F 0 "#PWR04" H 3950 2150 50  0001 C CNN
F 1 "GND" H 3950 2250 50  0000 C CNN
F 2 "" H 3950 2400 50  0001 C CNN
F 3 "" H 3950 2400 50  0001 C CNN
	1    3950 2400
	1    0    0    -1  
$EndComp
$Comp
L R R2
U 1 1 5AA9DEC1
P 4150 3100
F 0 "R2" V 4230 3100 50  0000 C CNN
F 1 "1K" V 4150 3100 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 4080 3100 50  0001 C CNN
F 3 "" H 4150 3100 50  0001 C CNN
	1    4150 3100
	-1   0    0    1   
$EndComp
$Comp
L R R1
U 1 1 5AA9E017
P 4050 4000
F 0 "R1" V 4130 4000 50  0000 C CNN
F 1 "100K" V 4050 4000 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 3980 4000 50  0001 C CNN
F 3 "" H 4050 4000 50  0001 C CNN
	1    4050 4000
	-1   0    0    1   
$EndComp
$Comp
L R R6
U 1 1 5AA9E04C
P 4550 3100
F 0 "R6" V 4630 3100 50  0000 C CNN
F 1 "100K" V 4550 3100 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 4480 3100 50  0001 C CNN
F 3 "" H 4550 3100 50  0001 C CNN
	1    4550 3100
	-1   0    0    1   
$EndComp
$Comp
L R R5
U 1 1 5AA9E082
P 4350 3100
F 0 "R5" V 4430 3100 50  0000 C CNN
F 1 "1K" V 4350 3100 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 4280 3100 50  0001 C CNN
F 3 "" H 4350 3100 50  0001 C CNN
	1    4350 3100
	-1   0    0    1   
$EndComp
$Comp
L R R7
U 1 1 5AA9E531
P 4600 4050
F 0 "R7" V 4680 4050 50  0000 C CNN
F 1 "100K" V 4600 4050 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 4530 4050 50  0001 C CNN
F 3 "" H 4600 4050 50  0001 C CNN
	1    4600 4050
	0    1    1    0   
$EndComp
Wire Wire Line
	4750 4050 4900 4050
$Comp
L GND #PWR05
U 1 1 5AA9E6FB
P 4300 4650
F 0 "#PWR05" H 4300 4400 50  0001 C CNN
F 1 "GND" H 4300 4500 50  0000 C CNN
F 2 "" H 4300 4650 50  0001 C CNN
F 3 "" H 4300 4650 50  0001 C CNN
	1    4300 4650
	1    0    0    -1  
$EndComp
Wire Wire Line
	4300 4050 4300 4650
$Comp
L R R8
U 1 1 5AA9E9E1
P 4600 4500
F 0 "R8" V 4680 4500 50  0000 C CNN
F 1 "20K" V 4600 4500 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 4530 4500 50  0001 C CNN
F 3 "" H 4600 4500 50  0001 C CNN
	1    4600 4500
	0    1    1    0   
$EndComp
Wire Wire Line
	4900 4500 4750 4500
Wire Wire Line
	4450 4500 4300 4500
Connection ~ 4300 4500
$Comp
L +3V3 #PWR06
U 1 1 5AA9EC5E
P 4450 2750
F 0 "#PWR06" H 4450 2600 50  0001 C CNN
F 1 "+3V3" H 4450 2890 50  0000 C CNN
F 2 "" H 4450 2750 50  0001 C CNN
F 3 "" H 4450 2750 50  0001 C CNN
	1    4450 2750
	1    0    0    -1  
$EndComp
$Comp
L C C1
U 1 1 5AA9EDEF
P 4450 2350
F 0 "C1" H 4475 2450 50  0000 L CNN
F 1 "0.47u" H 4475 2250 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 4488 2200 50  0001 C CNN
F 3 "" H 4450 2350 50  0001 C CNN
	1    4450 2350
	0    1    1    0   
$EndComp
Wire Wire Line
	4750 2550 4900 2550
Wire Wire Line
	4750 2200 4750 2550
Wire Wire Line
	4600 2350 4750 2350
Connection ~ 4750 2350
Wire Wire Line
	4300 2350 3950 2350
Wire Wire Line
	3950 2350 3950 2400
$Comp
L GND #PWR07
U 1 1 5AA9F4CE
P 5900 6350
F 0 "#PWR07" H 5900 6100 50  0001 C CNN
F 1 "GND" H 5900 6200 50  0000 C CNN
F 2 "" H 5900 6350 50  0001 C CNN
F 3 "" H 5900 6350 50  0001 C CNN
	1    5900 6350
	1    0    0    -1  
$EndComp
Wire Wire Line
	5350 6050 5350 6250
Wire Wire Line
	5350 6250 6500 6250
Wire Wire Line
	5900 6050 5900 6350
Connection ~ 5900 6250
Wire Wire Line
	5500 6050 5500 6250
Connection ~ 5500 6250
Wire Wire Line
	6350 6250 6350 6050
Wire Wire Line
	6500 6250 6500 6050
Connection ~ 6350 6250
$Comp
L +3V3 #PWR08
U 1 1 5AA9FB8D
P 5450 1150
F 0 "#PWR08" H 5450 1000 50  0001 C CNN
F 1 "+3V3" H 5450 1290 50  0000 C CNN
F 2 "" H 5450 1150 50  0001 C CNN
F 3 "" H 5450 1150 50  0001 C CNN
	1    5450 1150
	1    0    0    -1  
$EndComp
$Comp
L +3V3 #PWR09
U 1 1 5AA9FE0E
P 6100 1350
F 0 "#PWR09" H 6100 1200 50  0001 C CNN
F 1 "+3V3" H 6100 1490 50  0000 C CNN
F 2 "" H 6100 1350 50  0001 C CNN
F 3 "" H 6100 1350 50  0001 C CNN
	1    6100 1350
	1    0    0    -1  
$EndComp
$Comp
L C C3
U 1 1 5AA9FE43
P 5250 1400
F 0 "C3" H 5275 1500 50  0000 L CNN
F 1 "0.47u" H 5275 1300 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 5288 1250 50  0001 C CNN
F 3 "" H 5250 1400 50  0001 C CNN
	1    5250 1400
	-1   0    0    1   
$EndComp
$Comp
L C C2
U 1 1 5AA9FEB6
P 5100 1900
F 0 "C2" H 5125 2000 50  0000 L CNN
F 1 "0.1u" H 5125 1800 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 5138 1750 50  0001 C CNN
F 3 "" H 5100 1900 50  0001 C CNN
	1    5100 1900
	-1   0    0    1   
$EndComp
$Comp
L C C5
U 1 1 5AA9FEFE
P 5850 1400
F 0 "C5" H 5875 1500 50  0000 L CNN
F 1 "0.47u" H 5875 1300 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 5888 1250 50  0001 C CNN
F 3 "" H 5850 1400 50  0001 C CNN
	1    5850 1400
	-1   0    0    1   
$EndComp
$Comp
L C C4
U 1 1 5AA9FF59
P 5700 1850
F 0 "C4" H 5725 1950 50  0000 L CNN
F 1 "0.1u" H 5725 1750 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 5738 1700 50  0001 C CNN
F 3 "" H 5700 1850 50  0001 C CNN
	1    5700 1850
	-1   0    0    1   
$EndComp
Wire Wire Line
	5400 1200 5400 2050
Wire Wire Line
	5400 1200 5550 1200
Wire Wire Line
	5450 1200 5450 1150
Wire Wire Line
	5550 1200 5550 2050
Connection ~ 5450 1200
Wire Wire Line
	5250 1250 5400 1250
Connection ~ 5400 1250
Wire Wire Line
	5850 1250 5550 1250
Connection ~ 5550 1250
Wire Wire Line
	5100 1750 5400 1750
Connection ~ 5400 1750
Wire Wire Line
	5700 1700 5550 1700
Connection ~ 5550 1700
$Comp
L GND #PWR010
U 1 1 5AAA086A
P 5850 1550
F 0 "#PWR010" H 5850 1300 50  0001 C CNN
F 1 "GND" H 5850 1400 50  0000 C CNN
F 2 "" H 5850 1550 50  0001 C CNN
F 3 "" H 5850 1550 50  0001 C CNN
	1    5850 1550
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR011
U 1 1 5AAA0A6D
P 5700 2000
F 0 "#PWR011" H 5700 1750 50  0001 C CNN
F 1 "GND" H 5700 1850 50  0000 C CNN
F 2 "" H 5700 2000 50  0001 C CNN
F 3 "" H 5700 2000 50  0001 C CNN
	1    5700 2000
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR012
U 1 1 5AAA0AC0
P 5100 2050
F 0 "#PWR012" H 5100 1800 50  0001 C CNN
F 1 "GND" H 5100 1900 50  0000 C CNN
F 2 "" H 5100 2050 50  0001 C CNN
F 3 "" H 5100 2050 50  0001 C CNN
	1    5100 2050
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR013
U 1 1 5AAA0B01
P 5250 1550
F 0 "#PWR013" H 5250 1300 50  0001 C CNN
F 1 "GND" H 5250 1400 50  0000 C CNN
F 2 "" H 5250 1550 50  0001 C CNN
F 3 "" H 5250 1550 50  0001 C CNN
	1    5250 1550
	1    0    0    -1  
$EndComp
Wire Wire Line
	5900 2050 5900 1950
Wire Wire Line
	5900 1950 6250 1950
Wire Wire Line
	6100 1350 6100 1950
Wire Wire Line
	6250 1950 6250 2050
Connection ~ 6100 1950
$Comp
L C C7
U 1 1 5AAA0C1D
P 6600 1550
F 0 "C7" H 6625 1650 50  0000 L CNN
F 1 "0.47u" H 6625 1450 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 6638 1400 50  0001 C CNN
F 3 "" H 6600 1550 50  0001 C CNN
	1    6600 1550
	-1   0    0    1   
$EndComp
$Comp
L C C6
U 1 1 5AAA0C88
P 6400 1850
F 0 "C6" H 6425 1950 50  0000 L CNN
F 1 "0.1u" H 6425 1750 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 6438 1700 50  0001 C CNN
F 3 "" H 6400 1850 50  0001 C CNN
	1    6400 1850
	-1   0    0    1   
$EndComp
Wire Wire Line
	6400 1700 6100 1700
Connection ~ 6100 1700
Wire Wire Line
	6600 1400 6100 1400
Connection ~ 6100 1400
$Comp
L GND #PWR014
U 1 1 5AAA0F04
P 6400 2000
F 0 "#PWR014" H 6400 1750 50  0001 C CNN
F 1 "GND" H 6400 1850 50  0000 C CNN
F 2 "" H 6400 2000 50  0001 C CNN
F 3 "" H 6400 2000 50  0001 C CNN
	1    6400 2000
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR015
U 1 1 5AAA0F4B
P 6600 1700
F 0 "#PWR015" H 6600 1450 50  0001 C CNN
F 1 "GND" H 6600 1550 50  0000 C CNN
F 2 "" H 6600 1700 50  0001 C CNN
F 3 "" H 6600 1700 50  0001 C CNN
	1    6600 1700
	1    0    0    -1  
$EndComp
$Comp
L CONN_01X04 J2
U 1 1 5AAA1490
P 3800 3500
F 0 "J2" H 3800 3750 50  0000 C CNN
F 1 "CONN_01X04" V 3900 3500 50  0000 C CNN
F 2 "Pin_Headers:Pin_Header_Angled_1x04_Pitch1.27mm" H 3800 3500 50  0001 C CNN
F 3 "" H 3800 3500 50  0001 C CNN
	1    3800 3500
	-1   0    0    1   
$EndComp
Wire Wire Line
	4450 4050 4300 4050
Wire Wire Line
	4000 3550 4900 3550
Wire Wire Line
	4000 3450 4900 3450
Wire Wire Line
	4000 3350 4900 3350
$Comp
L CONN_01X01 J1
U 1 1 5AAA24C9
P 3450 4650
F 0 "J1" H 3450 4750 50  0000 C CNN
F 1 "CONN_01X01" V 3550 4650 50  0000 C CNN
F 2 "Pin_Headers:Pin_Header_Straight_1x01_Pitch1.27mm" H 3450 4650 50  0001 C CNN
F 3 "" H 3450 4650 50  0001 C CNN
	1    3450 4650
	-1   0    0    1   
$EndComp
Wire Wire Line
	4900 4900 4200 4900
Wire Wire Line
	4200 4900 4200 4650
Wire Wire Line
	4200 4650 3650 4650
$Comp
L Screw_Terminal_1x02 J5
U 1 1 5AAA2A35
P 8250 1350
F 0 "J5" H 8250 1600 50  0000 C TNN
F 1 "Screw_Terminal_1x02" V 8100 1350 50  0000 C TNN
F 2 "Pin_Headers:Pin_Header_Straight_1x02_Pitch2.54mm" H 8250 1125 50  0001 C CNN
F 3 "" H 8225 1350 50  0001 C CNN
	1    8250 1350
	-1   0    0    -1  
$EndComp
$Comp
L +3V3 #PWR016
U 1 1 5AAA316A
P 7850 1000
F 0 "#PWR016" H 7850 850 50  0001 C CNN
F 1 "+3V3" H 7850 1140 50  0000 C CNN
F 2 "" H 7850 1000 50  0001 C CNN
F 3 "" H 7850 1000 50  0001 C CNN
	1    7850 1000
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR017
U 1 1 5AAA3289
P 7850 1600
F 0 "#PWR017" H 7850 1350 50  0001 C CNN
F 1 "GND" H 7850 1450 50  0000 C CNN
F 2 "" H 7850 1600 50  0001 C CNN
F 3 "" H 7850 1600 50  0001 C CNN
	1    7850 1600
	1    0    0    -1  
$EndComp
Wire Wire Line
	8050 1250 7850 1250
Wire Wire Line
	7850 1250 7850 1000
Wire Wire Line
	8050 1450 7850 1450
Wire Wire Line
	7850 1450 7850 1600
Wire Wire Line
	4550 3250 4550 3550
Connection ~ 4550 3550
Wire Wire Line
	4350 3250 4350 3450
Connection ~ 4350 3450
Wire Wire Line
	4150 3250 4150 3350
Connection ~ 4150 3350
Wire Wire Line
	4150 2950 4150 2850
Wire Wire Line
	4150 2850 4550 2850
Wire Wire Line
	4350 2850 4350 2950
Connection ~ 4350 2850
Wire Wire Line
	4550 2850 4550 2950
Wire Wire Line
	4450 2850 4450 2750
Connection ~ 4450 2850
Wire Wire Line
	4900 3650 4000 3650
Wire Wire Line
	4050 3850 4050 3650
Connection ~ 4050 3650
$Comp
L GND #PWR018
U 1 1 5AAA5323
P 4050 4250
F 0 "#PWR018" H 4050 4000 50  0001 C CNN
F 1 "GND" H 4050 4100 50  0000 C CNN
F 2 "" H 4050 4250 50  0001 C CNN
F 3 "" H 4050 4250 50  0001 C CNN
	1    4050 4250
	1    0    0    -1  
$EndComp
Wire Wire Line
	4050 4150 4050 4250
Wire Wire Line
	4500 5500 4550 5500
Wire Wire Line
	4500 5150 4550 5150
$EndSCHEMATC
