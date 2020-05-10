import kotlin.math.pow

class Board(private val n: Int) {

    private val empty = "___"
    private var moveCount = 0
    var isGameOver = false
    private var board = Array(n) { Array(n) {empty} }

    fun resetBoard() {
        board = Array(n) { Array(n) {empty} }
    }

    fun printBoard() {
        board.forEach { row ->
            row.forEach { element ->
                if (element == empty) {
                    print("|$element|")
                } else {
                    print("| $element |")
                }

            }
            println()
        }
        println()
    }

    fun placePiece(x: Int, y: Int, move: String) {
        if (!isGameOver
            && isPositionValid(x, y)
            && board[x][y] == empty) {
            moveCount++
            board[x][y] = move
            printBoard()
            isGameOver = isWinningMove(x, y, move) || isDraw()
            if (isGameOver && !isDraw()) {
                println("Winner winner chicken dinner")
            } else if (isDraw()) {
                println("Draw!")
            }
        }
    }

    private fun isPositionValid(x: Int, y: Int): Boolean {
        return ((x in 0 until n) && (y in 0 until n))
    }

    private fun isWinningMove(x: Int, y: Int, move: String): Boolean {

        // check the row
        for (i in 0 until n) {
            if (board[x][i] != move) {
                break
            }
            if (i == n - 1) {
                return true
            }
        }

        // Check the column
        for (i in 0 until n) {
            if (board[i][y] != move) {
                break
            }
            if (i == n - 1) {
                return true
            }
        }

        // Check the diagonal
        if (x == y) {
            for (i in 0 until n) {
                if (board[i][i] != move) {
                    break
                }
                if (i == n - 1) {
                    return true
                }
            }
        }

        // Check anti-diagonal
        if (x + y == n - 1) {
            for (i in 0 until n) {
                if (board[i][(n - 1) - i] != move) {
                    break
                }
                if (i == n - 1) {
                    return true
                }
            }
        }

        return false
    }

    fun isDraw(): Boolean {
        return (moveCount == (n.toDouble().pow(2) - 1).toInt())
    }

    fun resetGame() {
        resetBoard()
        isGameOver = false
        moveCount = 0
    }
}