package com.shubhamtripz.lokaljobs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _place = MutableLiveData<String>()
    val place: LiveData<String> get() = _place

    private val _salary = MutableLiveData<String>()
    val salary: LiveData<String> get() = _salary

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> get() = _phone

    private val _openings = MutableLiveData<String>()
    val openings: LiveData<String> get() = _openings

    private val _qualifications = MutableLiveData<String>()
    val qualifications: LiveData<String> get() = _qualifications

    private val _experience = MutableLiveData<String>()
    val experience: LiveData<String> get() = _experience

    private val _noOfApplications = MutableLiveData<String>()
    val noOfApplications: LiveData<String> get() = _noOfApplications

    private val _views = MutableLiveData<String>()
    val views: LiveData<String> get() = _views

    private val _companyName = MutableLiveData<String>()
    val companyName: LiveData<String> get() = _companyName

    private val _jobDescription = MutableLiveData<String>()
    val jobDescription: LiveData<String> get() = _jobDescription

    private val _whatsapp = MutableLiveData<String>()
    val whatsapp: LiveData<String> get() = _whatsapp

    private val _jobRole = MutableLiveData<String>()
    val jobrole: LiveData<String> get() = _jobRole

    fun setTitle(value: String) { _title.value = value }
    fun setPlace(value: String) { _place.value = value }
    fun setSalary(value: String) { _salary.value = value }
    fun setPhone(value: String) { _phone.value = value }
    fun setOpenings(value: String) { _openings.value = value }
    fun setQualifications(value: String) { _qualifications.value = value }
    fun setExperience(value: String) { _experience.value = value }
    fun setNoOfApplications(value: String) { _noOfApplications.value = value }
    fun setViews(value: String) { _views.value = value }
    fun setCompanyName(value: String) { _companyName.value = value }
    fun setJobDescription(value: String) { _jobDescription.value = value }
    fun setwhatsappno(value: String) { _whatsapp.value = value }
    fun setJobRole(value: String) { _jobRole.value = value }
}
