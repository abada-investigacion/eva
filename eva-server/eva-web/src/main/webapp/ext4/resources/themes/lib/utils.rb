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
module ExtJS4
  module SassExtensions
    module Functions
      module Utils
        def parsebox(list, n)
          assert_type n, :Number
          if !n.int?
            raise ArgumentError.new("List index #{n} must be an integer")
          elsif n.to_i < 1
            raise ArgumentError.new("List index #{n} must be greater than or equal to 1")
          elsif n.to_i > 4
            raise ArgumentError.new("A box string can't contain more then 4")
          end

          new_list = list.clone.to_a
          size = new_list.size
                      
          if n.to_i >= size
            if size == 1
              new_list[1] = new_list[0]
              new_list[2] = new_list[0]
              new_list[3] = new_list[0]
            elsif size == 2
              new_list[2] = new_list[0]
              new_list[3] = new_list[1]
            elsif size == 3
              new_list[3] = new_list[1]
            end
          end
          
          new_list.to_a[n.to_i - 1]
        end
        
        def parseint(value)
          Sass::Script::Number.new(value.to_i)
        end
        
        # Returns a background-image property for a specified images for the theme
        def theme_image(theme, path, without_url = false, relative = false)
          path = path.value
          theme = theme.value
          without_url = (without_url.class == FalseClass) ? without_url : without_url.value
          
          relative_path = "../images/"
          
          if relative
            if relative.class == Sass::Script::String
              relative_path = relative.value
              relative = true
            elsif relative.class == FalseClass || relative.class == TrueClass
              relative = relative
            else
              relative = relative.value
            end
          else
            relative = false
          end
          
          if relative
            image_path = File.join(relative_path, theme, path)
          else
            images_path = File.join($ext_path, 'resources', 'themes', 'images', theme)
            image_path = File.join(images_path, path)
          end
          
          if !without_url
            url = "url('#{image_path}')"
          else
            url = "#{image_path}"
          end
          
          Sass::Script::String.new(url)
        end

        def theme_image_exists(path)
          result = false

          where_to_look = path.value.gsub('../../resources', 'resources')

          if where_to_look && FileTest.exists?("#{where_to_look}")
            result = true
          end

          return Sass::Script::Bool.new(result)
        end
      end
    end
  end
end

module Sass::Script::Functions
  include ExtJS4::SassExtensions::Functions::Utils
end