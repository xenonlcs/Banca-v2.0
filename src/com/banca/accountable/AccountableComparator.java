package com.banca.accountable;

import java.util.Comparator;

public class AccountableComparator implements Comparator<Accountable> {

    @Override
    public int compare(Accountable accountable, Accountable t1) {
        return Double.compare(accountable.getAmount(), t1.getAmount());
    }
}
