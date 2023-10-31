package com.example.weatherapptogit.core

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {
    private val TAG = "DateUtil"
    /**
     * Все возможные форматы даты, которые поступают в приложение или выводятся на экран
     * Для единообразия называть так: "yyyy-MM-dd HH:mm:ss" -> "YYYY_MM_DD__hhmmss"
     * Год/Месяц/День - Верхний регистр, Час/Минута/Секунда - Нижний регистр
     * ":" - Слитно (например: HH:mm:ss -> hhmmss)
     * "-" - Нижнее подчеркивание (yyyy-MM-dd -> YYYY_MM_DD)
     * " " - (Пробел) двойное подчеркивание
     */
    enum class Patterns(val pattern: String) {
        hhmm__DD_MM_YYYY("HH:mm dd-MM-yyyy"),
        hhmm__DD_MM_YY_Dot("HH:mm dd.MM.yy"),
        hhmm__D_MM_YY_Dot("HH:mm d.MM.yy"),
        YYYY_MM_DD__hhmmss("yyyy-MM-dd HH:mm:ss"),
        YYYY_MM_DD__hhmm("yyyy-MM-dd HH:mm"),
        DD_MM_YYYY__hhmmss("dd.MM.yyyy HH:mm:ss"),
        DD_MM_YYYY__hhmm("dd.MM.yyyy HH:mm"),
        hhmm("HH:mm"),
    }

    /**
     * Перевод из строки в LocalDateTime используя [patterns]
     * Будет происходить посик по всем [patterns], пока не найдется тот, в который получится перевести,
     * если среди поступивших не будет нужного вернет null
     */

}