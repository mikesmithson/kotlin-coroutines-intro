package contributors

import io.reactivex.Observable
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Response
import retrofit2.mock.Calls

object MockGithubService : GitHubService {
    override fun getOrgReposCall(org: String): Call<List<Repo>> {
        return Calls.response(repos)
    }

    override fun getRepoContributorsCall(owner: String, repo: String): Call<List<User>> {
        return Calls.response(reposMap.getValue(repo).users)
    }

    override suspend fun getOrgRepos(org: String): Response<List<Repo>> {
        delay(getReposDelay)
        return Response.success(repos)
    }

    override suspend fun getRepoContributors(owner: String, repo: String): Response<List<User>> {
        val testRepo = reposMap.getValue(repo)
        delay(testRepo.delay)
        return Response.success(testRepo.users)
    }

    override fun getOrgReposRx(org: String): Observable<Response<List<Repo>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRepoContributorsRx(owner: String, repo: String): Observable<Response<List<User>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}