package jp.ac.itcollege.s21010.newsrelease.presentation.contoller

import jp.ac.itcollege.s21010.newsrelease.application.service.NewsService
import jp.ac.itcollege.s21010.newsrelease.presentation.form.GetNewsDetailResponse
import jp.ac.itcollege.s21010.newsrelease.presentation.form.GetNewsListResponse
import jp.ac.itcollege.s21010.newsrelease.presentation.form.NewsInfo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("news")
@CrossOrigin(origins = ["http://localhost:8081"], allowCredentials = "true")

class NewsController(
    private val newsService: NewsService
){
    @GetMapping("/list")
    fun getList(): GetNewsListResponse {
        val newsList = newsService.getList().map {
            NewsInfo(it)
        }
        return GetNewsListResponse(newsList)
    }

    @GetMapping("/detail/{news_id}")
    fun getDetail(@PathVariable("news_id") newsId: Long) : GetNewsDetailResponse {
        val news = newsService.getDetail(newsId)
        return GetNewsDetailResponse(news)
    }
}