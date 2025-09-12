package com.epam.rd.contactbook;

public class Contact {
    private String name;
    private ContactInfo phoneNumber;
    private Email[] emails;
    private int emailCount;
    private Social[] socialMedia;
    private int socialMediaCount;

    public Contact(String contactName) {
        if (contactName == null || contactName.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact name cannot be null or empty");
        }
        this.name = contactName;
        this.emails = new Email[3];
        this.emailCount = 0;
        this.socialMedia = new Social[5];
        this.socialMediaCount = 0;
    }

    public void rename(String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            return; // Don't rename if new name is null or empty
        }
        this.name = newName;
    }

    public Email addEmail(String localPart, String domain) {
        if (emailCount >= 3 || localPart == null || domain == null) {
            return null;
        }
        Email email = new Email(localPart + "@" + domain);
        emails[emailCount++] = email;
        return email;
    }

    public Email addEpamEmail(String firstname, String lastname) {
        if (emailCount >= 3 || firstname == null || lastname == null) {
            return null;
        }
        Email epamEmail = new Email(firstname + "_" + lastname + "@epam.com") {
            @Override
            public String getTitle() {
                return "Epam Email";
            }
        };
        emails[emailCount++] = epamEmail;
        return epamEmail;
    }

    public ContactInfo addPhoneNumber(int code, String number) {
        if (phoneNumber != null || number == null) {
            return null;
        }
        phoneNumber = new ContactInfo() {
            @Override
            public String getTitle() {
                return "Tel";
            }

            @Override
            public String getValue() {
                return "+" + code + " " + number;
            }
        };
        return phoneNumber;
    }

    public Social addTwitter(String twitterId) {
        if (socialMediaCount >= 5 || twitterId == null) {
            return null;
        }
        Social twitter = new Social("Twitter", twitterId);
        socialMedia[socialMediaCount++] = twitter;
        return twitter;
    }

    public Social addInstagram(String instagramId) {
        if (socialMediaCount >= 5 || instagramId == null) {
            return null;
        }
        Social instagram = new Social("Instagram", instagramId);
        socialMedia[socialMediaCount++] = instagram;
        return instagram;
    }

    public Social addSocialMedia(String title, String id) {
        if (socialMediaCount >= 5 || title == null || id == null) {
            return null;
        }
        Social social = new Social(title, id);
        socialMedia[socialMediaCount++] = social;
        return social;
    }

    public ContactInfo[] getInfo() {
        // Count total entries
        int totalEntries = 1; // Name is always present
        if (phoneNumber != null) {
            totalEntries++;
        }
        totalEntries += emailCount;
        totalEntries += socialMediaCount;

        ContactInfo[] result = new ContactInfo[totalEntries];
        int index = 0;

        // Add name
        result[index++] = new NameContactInfo();

        // Add phone number if present
        if (phoneNumber != null) {
            result[index++] = phoneNumber;
        }

        // Add emails in order
        for (int i = 0; i < emailCount; i++) {
            result[index++] = emails[i];
        }

        // Add social media in order
        for (int i = 0; i < socialMediaCount; i++) {
            result[index++] = socialMedia[i];
        }

        return result;
    }

    // Private non-static nested class for name
    private class NameContactInfo implements ContactInfo {
        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getValue() {
            return name;
        }
    }

    // Public static nested class for Email
    public static class Email implements ContactInfo {
        private String email;

        public Email(String email) {
            this.email = email;
        }

        @Override
        public String getTitle() {
            return "Email";
        }

        @Override
        public String getValue() {
            return email;
        }
    }

    // Public static nested class for Social media
    public static class Social implements ContactInfo {
        private String title;
        private String id;

        public Social(String title, String id) {
            this.title = title;
            this.id = id;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getValue() {
            return id;
        }
    }
}