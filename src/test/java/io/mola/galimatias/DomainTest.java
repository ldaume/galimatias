/*
 * Copyright (c) 2013 Santiago M. Mola <santi@mola.io>
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a
 *   copy of this software and associated documentation files (the "Software"),
 *   to deal in the Software without restriction, including without limitation
 *   the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *   and/or sell copies of the Software, and to permit persons to whom the
 *   Software is furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 *   OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *   FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 *   DEALINGS IN THE SOFTWARE.
 */

package io.mola.galimatias;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.net.MalformedURLException;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(JUnit4.class)
public class DomainTest {

    @Test
    public void equals() throws GalimatiasParseException {
        final Domain sameDomain = Domain.parseDomain("example.com");
        assertThat(sameDomain).isEqualTo(sameDomain);
        assertThat(sameDomain).isEqualTo(Domain.parseDomain("example.com"));
        assertThat(sameDomain).isEqualTo(Domain.parseDomain("EXAMPLE.COM"));
        assertThat(sameDomain).isNotEqualTo(Domain.parseDomain("other.com"));
        assertThat(sameDomain).isNotEqualTo(Domain.parseDomain("other.example.com"));
        assertThat(sameDomain).isNotEqualTo("foo");
        assertThat(sameDomain).isNotEqualTo(null);
    }

    @Test
    public void parseDomainIDNA() throws GalimatiasParseException {
        assertThat(Domain.parseDomain("ジェーピーニック.jp").toString()).isEqualTo("xn--hckqz9bzb1cyrb.jp");
    }

    @Test(expected = GalimatiasParseException.class)
    public void parseDomainEmpty() throws GalimatiasParseException {
        Domain.parseDomain("");
    }

    @Test(expected = NullPointerException.class)
    public void parseDomainNull() throws GalimatiasParseException {
        Domain.parseDomain(null);
    }

    @Test(expected = GalimatiasParseException.class)
    public void parseDomainDot() throws GalimatiasParseException {
        Domain.parseDomain(".");
    }

}
