package ge.msda.myapplication.models

data class Note(
    val note: String
) {
    var id: Int = 0

    companion object {
        var sId = 0
    }

    init {
        this.id = ++sId
    }
}