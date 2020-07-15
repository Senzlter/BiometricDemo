package com.example.biometricdemo

import com.epson.epos2.printer.Printer

data class CmlPrinterLA (
    var nLengthCol1st: Int = 14,
    var nLengthCol2nd: Int = 14,
    var nLengthCol3rd: Int = 8,
    var nAlignCol1st:Int = Printer.ALIGN_LEFT,
    var nAlignCol2nd:Int = Printer.ALIGN_LEFT,
    var nAlignCol3rd:Int = Printer.ALIGN_LEFT
)