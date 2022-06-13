package com.felipearpa.mypost.company.mapper

import com.felipearpa.mypost.company.data.CompanyResponse
import com.felipearpa.mypost.company.domain.Company
import com.felipearpa.mypost.company.view.CompanyModel
import com.felipearpa.mypost.type.NonEmptyString

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