package com.pipel.mypost.company.mapper

import com.pipel.mypost.company.data.CompanyResponse
import com.pipel.mypost.company.domain.Company
import com.pipel.mypost.company.view.CompanyModel
import com.pipel.mypost.type.NonEmptyString

object CompanyMapper {

    fun mapFromDataToDomain(companyResponse: CompanyResponse): Company =
        Company(
            name = NonEmptyString(companyResponse.name),
            catchPhrase = NonEmptyString(companyResponse.catchPhrase),
            bs = NonEmptyString(companyResponse.bs)
        )

    fun mapFromDomainToView(company: Company): CompanyModel =
        CompanyModel(
            name = company.name.value,
            catchPhrase = company.catchPhrase.value,
            bs = company.bs.value
        )

}