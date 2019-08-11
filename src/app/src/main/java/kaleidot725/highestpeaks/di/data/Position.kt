package kaleidot725.highestpeaks.di.data

enum class PositionType {
    TopLeft,
    TopCenter,
    TopRight,
    CenterLeft,
    Center,
    CenterRight,
    BottomLeft,
    BottomCenter,
    BottomRight
}

data class Position(val type : PositionType, val detail : String)
