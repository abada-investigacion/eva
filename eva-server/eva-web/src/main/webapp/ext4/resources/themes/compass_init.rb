###
# #%L
# Eva
# %%
# Copyright (C) 2013 Abada Servicios Desarrollo (investigacion@abadasoft.com)
# %%
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as
# published by the Free Software Foundation, either version 3 of the 
# License, or (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public 
# License along with this program.  If not, see
# <http://www.gnu.org/licenses/gpl-3.0.html>.
# #L%
###
# include the utils rb file which has extra functionality for the ext theme
dir = File.dirname(__FILE__)
require File.join(dir, 'lib', 'utils.rb')

# register ext4 as a compass framework
Compass::Frameworks.register 'ext4', dir