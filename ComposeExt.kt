@Composable
fun Modifier.safeClickable(
    delayMs: Long = 300L,
    onClick: () -> Unit
): Modifier {
    var lastClickTime by remember { mutableStateOf(0L) }
    return clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    ) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime >= delayMs) {
            lastClickTime = currentTime
            onClick()
        }
    }
}
