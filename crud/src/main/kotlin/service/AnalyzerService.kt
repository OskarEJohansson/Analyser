package service


import model.request.AnalyzeRequest
import model.response.AnalyzeResponse



object AnalyzerService {

     suspend fun analyze(request: AnalyzeRequest): AnalyzeResponse {
         println(request.data)
        return AnalyzeResponse("Analyzed: ${request.data}")

    }
}