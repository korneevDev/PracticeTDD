package com.github.johnnysc.practicetdd

abstract class ViewModelChain(
    private val chain: FeatureChain.CheckAndHandle,
    private var nextChain: FeatureChain.Handle = FeatureChain.Handle.Empty
) : FeatureChain.Handle {
    fun nextFeatureChain(featureChain: FeatureChain.Handle) {
        nextChain = featureChain
    }

    override suspend fun handle(message: String): MessageUI {
        return with(message) {
            if (chain.canHandle(this))
                chain.handle(this)
            else
                nextChain.handle(this)
        }
    }
}
