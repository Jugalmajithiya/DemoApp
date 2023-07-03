package com.example.demoapplication.ui.home.data


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Result(
    @SerializedName("adverse_reactions")
    val adverseReactions: List<String?>?,
    @SerializedName("boxed_warning")
    val boxedWarning: List<String?>?,
    @SerializedName("carcinogenesis_and_mutagenesis_and_impairment_of_fertility")
    val carcinogenesisAndMutagenesisAndImpairmentOfFertility: List<String?>?,
    @SerializedName("clinical_pharmacology")
    val clinicalPharmacology: List<String?>?,
    @SerializedName("clinical_studies")
    val clinicalStudies: List<String?>?,
    @SerializedName("contraindications")
    val contraindications: List<String?>?,
    @SerializedName("description")
    val description: List<String?>?,
    @SerializedName("dosage_and_administration")
    val dosageAndAdministration: List<String?>?,
    @SerializedName("dosage_and_administration_table")
    val dosageAndAdministrationTable: List<String?>?,
    @SerializedName("dosage_forms_and_strengths")
    val dosageFormsAndStrengths: List<String?>?,
    @SerializedName("drug_interactions")
    val drugInteractions: List<String?>?,
    @SerializedName("drug_interactions_table")
    val drugInteractionsTable: List<String?>?,
    @SerializedName("effective_time")
    val effectiveTime: String?,
    @SerializedName("geriatric_use")
    val geriatricUse: List<String?>?,
    @SerializedName("how_supplied")
    val howSupplied: List<String?>?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("indications_and_usage")
    val indicationsAndUsage: List<String?>?,
    @SerializedName("information_for_patients")
    val informationForPatients: List<String?>?,
    @SerializedName("mechanism_of_action")
    val mechanismOfAction: List<String?>?,
    @SerializedName("nonclinical_toxicology")
    val nonclinicalToxicology: List<String?>?,
    @SerializedName("openfda")
    val openfda: Openfda?,
    @SerializedName("overdosage")
    val overdosage: List<String?>?,
    @SerializedName("package_label_principal_display_panel")
    val packageLabelPrincipalDisplayPanel: List<String?>?,
    @SerializedName("pediatric_use")
    val pediatricUse: List<String?>?,
    @SerializedName("pharmacodynamics")
    val pharmacodynamics: List<String?>?,
    @SerializedName("pharmacokinetics")
    val pharmacokinetics: List<String?>?,
    @SerializedName("pregnancy")
    val pregnancy: List<String?>?,
    @SerializedName("recent_major_changes")
    val recentMajorChanges: List<String?>?,
    @SerializedName("set_id")
    val setId: String?,
    @SerializedName("spl_medguide")
    val splMedguide: List<String?>?,
    @SerializedName("spl_medguide_table")
    val splMedguideTable: List<String?>?,
    @SerializedName("spl_product_data_elements")
    val splProductDataElements: List<String?>?,
    @SerializedName("use_in_specific_populations")
    val useInSpecificPopulations: List<String?>?,
    @SerializedName("version")
    val version: String?,
    @SerializedName("warnings_and_cautions")
    val warningsAndCautions: List<String?>?
) : Parcelable