package com.cg.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.tms.exceptions.PackageNotFoundException;

public interface IPackageRepository extends JpaRepository<Package,Integer>{

}
