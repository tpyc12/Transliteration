package com.myhome.android.transliteration

import java.lang.IllegalArgumentException
import java.lang.StringBuilder

class Translit {

    fun cyr2lat(ch: Char): String {
        return when (ch.toUpperCase()) {
            'А' -> "A"
            'Б' -> "B"
            'В' -> "V"
            'Г' -> "G"
            'Д' -> "D"
            'Е' -> "E"
            'Ё' -> "E"
            'Ж' -> "ZH"
            'З' -> "Z"
            'И' -> "I"
            'Й' -> "I"
            'К' -> "K"
            'Л' -> "L"
            'М' -> "M"
            'Н' -> "N"
            'О' -> "O"
            'П' -> "P"
            'Р' -> "R"
            'С' -> "S"
            'Т' -> "T"
            'У' -> "U"
            'Ф' -> "F"
            'Х' -> "KH"
            'Ц' -> "TS"
            'Ч' -> "CH"
            'Ш' -> "SH"
            'Щ' -> "SHCH"
            'Ъ' -> "IE"
            'Ы' -> "Y"
            'Ь' -> "-"
            'Э' -> "E"
            'Ю' -> "IU"
            'Я' -> "IA"
            else -> ch.toString()
        }
    }

    fun cyr2lat(s: String): String {
        val sb = StringBuilder(s.length * 2)
        for (ch in s.toCharArray()) {
            sb.append(cyr2lat(ch))
        }
        return sb.toString()
    }

    fun lat2cyr(s: String): String {
        val sb = StringBuilder(s.length)
        var i = 0
        while (i < s.length) { // Идем по строке слева направо. В принципе, подходит для обработки потока
            var ch = s[i]
            if (ch == 'I') { // Префиксная нотация вначале
                i++ // преходим ко второму символу сочетания
                ch = s[i]
                when (ch.toUpperCase()) {
                    'E' -> sb.append('Ъ')
                    'U' -> sb.append('Ю')
                    'A' -> sb.append('Я')
                    else -> throw IllegalArgumentException("Illegal transliterated symbol at position $i")
                }
            } else if (i + 1 < s.length && s[i + 1] == 'h' && !(i + 2 < s.length && s[i + 2] == 'h')) { // Постфиксная нотация, требует информации о двух следующих символах. Для потока придется сделать обертку с очередью из трех символов.
                when (ch.toUpperCase()) {
                    'Z' -> sb.append('Ж')
                    'K' -> sb.append('Х')
                    'C' -> sb.append('Ч')
                    'S' -> sb.append('Ш')
                    else -> throw IllegalArgumentException("Illegal transliterated symbol at position $i")
                }
                i++ // пропускаем постфикс
            } else { // одиночные символы
                when (ch.toUpperCase()) {
                    'A' -> sb.append('А')
                    'B' -> sb.append('Б')
                    'V' -> sb.append('В')
                    'G' -> sb.append('Г')
                    'D' -> sb.append('Д')
                    'E' -> sb.append('Е')
                    'Z' -> sb.append('З')
                    'I' -> sb.append('И')
                    'K' -> sb.append('К')
                    'L' -> sb.append('Л')
                    'M' -> sb.append('М')
                    'N' -> sb.append('Н')
                    'O' -> sb.append('О')
                    'P' -> sb.append('П')
                    'R' -> sb.append('Р')
                    'S' -> sb.append('С')
                    'T' -> sb.append('Т')
                    'U' -> sb.append('У')
                    'F' -> sb.append('Ф')
                    'C' -> sb.append('Ц')
                    'Y' -> sb.append('Ы')
                    else -> sb.append(ch)
                }
            }
            i++ // переходим к следующему символу
        }
        return sb.toString()
    }
}
