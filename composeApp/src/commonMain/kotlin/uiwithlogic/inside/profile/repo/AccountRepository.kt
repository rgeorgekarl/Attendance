package uiwithlogic.inside.profile.repo

import datasource.Supabase.client
import exceptions.YourNotLoggedInException
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import org.real.AppDatabase
import uiwithlogic.inside.profile.model.AccountDetailSer
import uiwithlogic.inside.profile.toAccountDetailSer

interface AccountRepository {
    suspend fun fetchAccountDetails()
    fun getAccountDetails(): AccountDetailSer
    suspend fun upsert(accountDetails: AccountDetailSer)
}

class AccountRepoImp(
    private val database: AppDatabase
): AccountRepository {
    private val queries = database.accountDetailsQueries

    override suspend fun fetchAccountDetails() {
        val accountDetails = client.from("accountDetails")
            .select().decodeSingle<AccountDetailSer>()
        queryUpsert(accountDetails)
    }

    override fun getAccountDetails(): AccountDetailSer = queries.getAccountDetails().executeAsOne().toAccountDetailSer()

    override suspend fun upsert(accountDetails: AccountDetailSer) {
        client.from("accountDetails")
            .upsert(accountDetails.copy(id = client.auth.currentUserOrNull()?.id ?: throw YourNotLoggedInException()))
        queryUpsert(accountDetails)
    }

    private fun queryUpsert(accountDetails: AccountDetailSer) {
        queries.insertOrReplace(
            id = accountDetails.id,
            givenId = accountDetails.givenId,
            firstName = accountDetails.firstName,
            middleName = accountDetails.middleName,
            lastName = accountDetails.lastName,
            nickName = accountDetails.nickName,
            bio = accountDetails.bio,
            birth = accountDetails.birth,
            age = accountDetails.age?.toLong(),
            school = accountDetails.school,
            schoolYear = accountDetails.schoolYear?.toLong(),
            course = accountDetails.course,
            contactNumber = accountDetails.contactNumber?.toLong(),
            address = accountDetails.address,
            email = accountDetails.email,
            icon = accountDetails.icon
        )
    }
}