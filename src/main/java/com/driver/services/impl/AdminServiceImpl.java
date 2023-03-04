package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
     Admin admin=new Admin();
     admin.setUserName(username);
     admin.setPassword(password);
     adminRepository1.save(admin);
     return admin;
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        Admin admin=adminRepository1.findById(adminId).get();
        ServiceProvider serviceProvider=new ServiceProvider();

        List<ServiceProvider>serviceProviderList=admin.getServiceProviders();
         serviceProvider.setName(providerName);
         serviceProviderList.add(serviceProvider);
         serviceProvider.setAdmin(admin);
         adminRepository1.save(admin);
         return admin;

    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{
       ServiceProvider serviceProvider=serviceProviderRepository1.findById(serviceProviderId).get();
        Country country=new Country();
        CountryName countryName1=CountryName.valueOf(countryName);
        country.setCountryName(countryName1);
        country.setCode(countryName1.toCode());
        List<Country>countries=serviceProvider.getCountryList();
        countries.add(country);
        country.setServiceProvider(serviceProvider);
        serviceProviderRepository1.save(serviceProvider);
        return serviceProvider;

//        String newCountry=countryName.toUpperCase();
//        CountryName enumCountry=null;
//        boolean isCountryValid=false;
//        for(CountryName name:CountryName.values()){
//            if(newCountry.equals(name)){
//                isCountryValid=true;
//                enumCountry=name;
//                break;
//            }
//        }
//        if(isCountryValid==false){
//            throw new Exception("Country not found");
//        }
//       if(enumCountry!=null){
//           country.setCountryName(enumCountry);
//           country.setCode(enumCountry.toCode());
//           country.setServiceProvider(null);
//       }
    }
}
