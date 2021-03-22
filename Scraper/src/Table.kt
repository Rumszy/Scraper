class Table (rows: Int, cols: Int) {
    var table = Array(rows) {Array(cols) {""} }
    

    fun setElement(row: Int, col: Int, text: String) {
        table[row][col] = text
    }

    fun getElement(row: Int, col: Int) {
        print(table[row][col])
    }
}