package org.foodie.pojo;

/**
 *
 * @author AFROZ
 */
public class OwnerProfile {

    private static String ownerName;
    private static String companyName;
    private static String companyId;

    public static String getOwnerName() {
        return ownerName;
    }

    public static void setOwnerName(String ownerName) {
        OwnerProfile.ownerName = ownerName;
    }

    public static String getCompanyName() {
        return companyName;
    }

    public static void setCompanyName(String companyName) {
        OwnerProfile.companyName = companyName;
    }

    public static String getCompanyId() {
        return companyId;
    }

    public static void setCompanyId(String companyId) {
        OwnerProfile.companyId = companyId;
    }

}
