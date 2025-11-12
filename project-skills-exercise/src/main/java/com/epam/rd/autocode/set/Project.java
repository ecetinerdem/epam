// Project.java
package com.epam.rd.autocode.set;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Project {

    private List<Role> roles;

    private static class Entry {
        private Level level;
        private Skill skill;

        public Entry(Level level, Skill skill) {
            this.level = level;
            this.skill = skill;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return level == entry.level && skill == entry.skill;
        }

        @Override
        public int hashCode() {
            return Objects.hash(level, skill);
        }
    }

    public Project(Role... roles) {
        this.roles = new ArrayList<>();
        for (Role role : roles) {
            this.roles.add(role);
        }
    }

    public List<Role> getRoles() {
        return roles;
    }

    public int getConformity(Set<Member> team) {
        // Generate list of pairs <Level, Skill> for project roles
        List<Entry> projectEntries = new ArrayList<>();
        for (Role role : roles) {
            Level roleLevel = role.getLevel();
            for (Skill skill : role.getSkills()) {
                projectEntries.add(new Entry(roleLevel, skill));
            }
        }

        // Save original size
        int originalSize = projectEntries.size();

        // Handle edge case of empty project
        if (originalSize == 0) {
            return 100;
        }

        // Generate list of pairs <Level, Skill> for team members
        List<Entry> teamEntries = new ArrayList<>();
        for (Member member : team) {
            Level memberLevel = member.getLevel();
            for (Skill skill : member.getSkills()) {
                teamEntries.add(new Entry(memberLevel, skill));
            }
        }

        // Remove common elements from both lists
        // Use iterator approach to properly remove matched entries
        for (int i = projectEntries.size() - 1; i >= 0; i--) {
            Entry projectEntry = projectEntries.get(i);
            for (int j = 0; j < teamEntries.size(); j++) {
                Entry teamEntry = teamEntries.get(j);
                if (projectEntry.equals(teamEntry)) {
                    projectEntries.remove(i);
                    teamEntries.remove(j);
                    break;
                }
            }
        }

        // Calculate conformity percentage
        int conformity = ((originalSize - projectEntries.size()) * 100) / originalSize;

        return conformity;
    }
}