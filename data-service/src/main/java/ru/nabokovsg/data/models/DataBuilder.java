package ru.nabokovsg.data.models;

import ru.nabokovsg.data.models.enums.BuilderType;

import java.util.List;
import java.util.Map;

public class DataBuilder {

    private final List<Long> objectTypeIds;
    private final BuilderType type;
    private final List<Building> buildings;
    private final List<ObjectsType> objectsTypes;
    private final Map<Long, Department> departments;
    private final Map<Long, Address> addresses;
    private final List<Element> elements;
    private final List<Defect> defects;
    private final List<Documentation> documentations;
    private final Map<Long, Organization> organizations;
    private final Map<Long, Employee> employees;
    private final Map<Long, SurveyObject> surveyObjects;
    private final Map<Long, ReportingDocument> reportingDocuments;

    private DataBuilder(Data data) {
        this.objectTypeIds = data.objectTypeIds;
        this.buildings = data.buildings;
        this.objectsTypes = data.objectsTypes;
        this.departments = data.departments;
        this.addresses = data.addresses;
        this.type =  data.type;
        this.elements =  data.elements;
        this.defects =  data.defects;
        this.documentations =  data.documentations;
        this.organizations = data.organizations;
        this.employees = data.employees;
        this.surveyObjects = data.surveyObjects;
        this.reportingDocuments = data.reportingDocuments;
    }

    public List<Long> getObjectTypeIds() {
        return objectTypeIds;
    }

    public BuilderType getType() {
        return type;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public List<ObjectsType> getObjectsTypes() {
        return objectsTypes;
    }

    public Map<Long, Department> getDepartments() {
        return departments;
    }

    public Map<Long, Address> getAddresses() {
        return addresses;
    }

    public List<Element> getElements() {
        return elements;
    }

    public List<Defect> getDefects() {
        return defects;
    }

    public List<Documentation> getDocumentations() {
        return documentations;
    }

    public Map<Long, Organization> getOrganizations() {
        return organizations;
    }

    public Map<Long, Employee> getEmployees() {
        return employees;
    }

    public Map<Long, SurveyObject> getSurveyObjects() {
        return surveyObjects;
    }

    public Map<Long,ReportingDocument> getReportingDocuments() {
        return reportingDocuments;
    }

    public static class Data {

        private List<Long> objectTypeIds;
        private List<Building> buildings;
        private List<ObjectsType> objectsTypes;
        private Map<Long, Department> departments;
        private Map<Long, Address> addresses;
        private BuilderType type;
        private List<Element> elements;
        private List<Defect> defects;
        private List<Documentation> documentations;
        private Map<Long, Organization> organizations;
        private Map<Long, Employee> employees;
        private Map<Long, SurveyObject> surveyObjects;
        private Map<Long,ReportingDocument> reportingDocuments;

        public Data ids(List<Long> objectTypeIds) {
            this.objectTypeIds = objectTypeIds;
            return this;
        }

        public Data buildings(List<Building> buildings) {
            this.buildings = buildings;
            return this;
        }

        public Data objectsTypes(List<ObjectsType> objectsTypes) {
            this.objectsTypes = objectsTypes;
            return this;
        }

        public Data departments(Map<Long, Department> departments) {
            this.departments = departments;
            return this;
        }

        public Data addresses(Map<Long, Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public Data elements(List<Element> elements) {
            this.elements = elements;
            return this;
        }

        public Data type(BuilderType type) {
            this.type = type;
            return this;
        }

        public Data defects(List<Defect> defects) {
            this.defects = defects;
            return this;
        }

        public Data documentations(List<Documentation> documentations) {
            this.documentations = documentations;
            return this;
        }

        public Data organizations(Map<Long, Organization> organizations) {
            this.organizations = organizations;
            return this;
        }

        public Data employees(Map<Long, Employee> employees) {
            this.employees = employees;
            return this;
        }

        public Data surveyObjects( Map<Long, SurveyObject> surveyObjects) {
            this.surveyObjects = surveyObjects;
            return this;
        }

        public Data reportingDocuments(Map<Long, ReportingDocument> reportingDocuments) {
            this.reportingDocuments = reportingDocuments;
            return this;
        }

        public DataBuilder build() {
            return new DataBuilder(this);
        }
    }
}