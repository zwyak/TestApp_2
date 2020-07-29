package pojo

public data class Data(
    val id: Int,
    val name: String,
    val status: Int


) {
    override fun toString(): String {
        return name
    }
}