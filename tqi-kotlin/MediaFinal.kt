fun main() {
    // val media = readLine()!!.toDouble();
    val media = 7
    val arr = arrayOf("REP", "REC", "APR")
    when {
        media < 5 -> print(arr[0])
        media >= 7 -> print(arr[2])
        else -> print(arr[1])
    }
}