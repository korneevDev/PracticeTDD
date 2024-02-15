package com.github.johnnysc.practicetdd

class ViewModelChain(private val featureChain: FeatureChain) : FeatureChain.Handle {

    private var nextChain : FeatureChain = FeatureChain.Empty()
    fun nextFeatureChain(nextFeatureChain: FeatureChain) {
        nextChain = nextFeatureChain
    }

    override suspend fun handle(message: String): MessageUI =
        if(featureChain.canHandle(message))
            featureChain.handle(message)
        else
            nextChain.handle(message)

}
